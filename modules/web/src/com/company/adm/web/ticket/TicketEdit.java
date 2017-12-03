package com.company.adm.web.ticket;

import com.company.adm.entity.*;
import com.company.adm.entity.contracts.Comment;
import com.company.adm.entity.contracts.Contract;
import com.company.adm.entity.scheduler.CustomScheduler;
import com.company.adm.service.AdmConfig;
import com.company.adm.service.TicketService;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.chile.core.model.MetaProperty;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.reports.gui.actions.EditorPrintFormAction;
import com.vaadin.data.util.FilesystemContainer;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TicketEdit extends AbstractEditor<Ticket> {
    @Named("fieldGroup.source")
    private LookupField sourceField;
    @Named("fieldGroup2.statusDateTime")
    private DateField statusDateTimeField;
    @Named("fieldGroup.face")
    private LookupField faceField;
    @Named("fieldGroup.status")
    private PickerField statusField;
    @Named("fieldGroup.name")
    private ResizableTextArea nameField;
    @Named("fieldGroup2.characteristic")
    private ResizableTextArea characteristicField;
    @Named("fieldGroup.interest")
    private LookupPickerField interestField;
    @Inject
    private CollectionDatasource<CustomScheduler, UUID> customSchedulerDs;
    @Inject
    private Button sendEmailButton;
    @Inject
    private Button reportsButton;
    @Inject
    private Metadata metadata;
    @Inject
    private TicketService ticketService;
    @Inject
    private CollectionDatasource<Comment, UUID> historyDs;
    @Inject
    private UserSessionSource userSessionSource;
    @Named("fieldGroup2.phone")
    private MaskedField phoneField;
    @Inject
    private Datasource<Questionnaire> questionnaireDs;
    @Inject
    private UserSession userSession;
    @Inject
    private ResizableTextArea newCommentText;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private AdmConfig admConfig;
    @Inject
    private Datasource<Ticket> ticketDs;

    /**
     * Called by the framework after creation of all components and before showing the screen.
     * <br> Override this method and put initialization logic here.
     *
     * @param params parameters passed from caller's code, usually from
     *               {@link #openWindow(String, WindowManager.OpenType)} and similar methods, or set in
     *               {@code screens.xml} for this registered screen
     */
    @Override
    public void init(Map<String, Object> params) {
        //Просто коммент
        nameField.setResizable(true);
        characteristicField.setResizable(true);
        interestField.removeAllActions();
        interestField.addLookupAction();
        statusField.addValueChangeListener(e -> {
            if (!((TicketState) e.getValue()).getHasDateTime()) {
                getItem().setStatusDateTime(null);
                statusDateTimeField.setVisible(false);
                statusDateTimeField.setRequired(false);
            } else {
                statusDateTimeField.setVisible(true);
                statusDateTimeField.setRequired(true);
            }
        });
        faceField.addValueChangeListener(e -> {
            if (e.getValue() == Face.entity) {
                sendEmailButton.setVisible(true);
            } else {
                sendEmailButton.setVisible(false);
            }
        });
    }

    @Override
    protected void postInit() {
        //Устанавливаем действие для кнопки отчетов
        reportsButton.setAction(new EditorPrintFormAction("report", this, String.format("Карточка заявки %s", getItem().getName() != null ? getItem().getName() : "")));
        ((EditorPrintFormAction) reportsButton.getAction()).setBeforeActionPerformedHandler(() -> commit());
        if (getItem().getQuestionnaire() == null)
            getItem().setQuestionnaire(metadata.create(Questionnaire.class));
    }

    public void onCreateContract(Component source) {
        if (!validateAll())
            return;
        TicketState defaultClosedStatus = ticketService.getDefaultClosedStatus();
        if (defaultClosedStatus == null) {
            showNotification("Не найден статус заявки при заключении договора", NotificationType.HUMANIZED);
            return;
        }
        getItem().setStatus(defaultClosedStatus);
        commit();
        getWindowManager().close(this);
        Contract contract = metadata.create(Contract.class);
        Map<String, Object> params = new HashMap<>();
        params.put("TICKET", getItem());
        openEditor(contract, WindowManager.OpenType.THIS_TAB, params);
    }

    public void onSchedulerButtonClick() {
        if (!commit(true))
            return;
        Map<String, Object> params = new HashMap<>();
        params.put("ITEM", getItem());
        openWindow("scheduler-add-screen", WindowManager.OpenType.DIALOG, params);
    }

    public void onNextTicket(Component source) {
        validateAll();
        commit(true);
        getWindowManager().close(this);
        openEditor(metadata.create(Ticket.class), WindowManager.OpenType.THIS_TAB);
    }

    public void onSmsButtonClick() {
        showOptionDialog("Подтверждение", "Перед отправкой смс все изменения будут сохранены. Вы уверены?", MessageType.CONFIRMATION,
                new Action[]{
                        new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(actionPerformedEvent -> {
                            boolean commited = commit();
                            if(!commited)
                                return;
                            sendSms();
                        }),
                        new DialogAction(DialogAction.Type.NO, Action.Status.NORMAL)
                });
    }

    private void sendSms() {
        if (admConfig.getPhoneIpAddress() == null || admConfig.getPhonePingPortNumber() == null || admConfig.getPhoneSmsPortNumber() == null) {
            showNotification("Отправка смс невозможна", "Не указаны параметры подключения к телефону", NotificationType.ERROR);
            return;
        }
        AbstractWindow smsWindow = openWindow("send-sms", WindowManager.OpenType.DIALOG,
                ParamsMap.of(
                        "TICKET", getItem()
                ));
        smsWindow.addCloseListener(actionId -> {
            if (actionId.equals("success")) {
                showNotification(getMessage("smsSent"), NotificationType.TRAY);
                ticketDs.refresh();
                historyDs.refresh();
            }
            else if (actionId.equals("error"))
                showNotification("Ошибка при отправке смс. Обратитесь к системному администратору", NotificationType.ERROR);
            else if (actionId.equals("close"))
                showNotification("Отправка смс отменена", NotificationType.TRAY);
            else
                showNotification("Неизвестная ошибка при отправке смс. Обратитесь к системному администратору", NotificationType.ERROR);
        });
    }

    /**
     * Hook to be implemented in subclasses. Called by the framework when all validation is done and datasources are
     * going to be committed.
     *
     * @return true to continue, false to abort
     */
    @Override
    protected boolean preCommit() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (CustomScheduler scheduler : getItem().getCustomScheduler()) {
            if (PersistenceHelper.isNew(scheduler)) {
                customSchedulerDs.addItem(scheduler);
                Comment comment = metadata.create(Comment.class);
                comment.setComment(String.format("Задача: %s, %s", format.format(scheduler.getDate()), scheduler.getComment()));
                comment.setUser(userSessionSource.getUserSession().getUser());
                comment.setDateTime(new Date());
                comment.setTicket(getItem());
                historyDs.addItem(comment);
            }
        }
        return super.preCommit();
    }


    /**
     * Check validity by invoking validators on all components which support them
     * and show validation result notification. This method also calls {@link #postValidate(ValidationErrors)} hook to
     * support additional validation.
     * <p>You should override this method in subclasses ONLY if you want to completely replace the validation process,
     * otherwise use {@link #postValidate(ValidationErrors)}.
     *
     * @return true if the validation was successful, false if there were any problems
     */
    @Override
    public boolean validateAll() {
        if (getItem().getQuestionnaire().getName() == null || getItem().getQuestionnaire().getPhone() == null) {
            questionnaireDs.getItem().setName(getItem().getName());
            questionnaireDs.getItem().setPhone(getItem().getPhone());
        }
        return super.validateAll();
    }

    public void onSendEmailButtonClick() {
        if (getItem().getEMail() == null) {
            showNotification("Ошибка!", "Укажите электронный адрес клиента", NotificationType.ERROR);
            return;
        }
        showOptionDialog(
                "Сохранение",
                "Отправка e-mail вызовет сохранение заявки. Продолжить?",
                MessageType.CONFIRMATION,
                new Action[]{
                        new DialogAction(DialogAction.Type.YES) {
                            @Override
                            public void actionPerform(Component component) {
                                sendByEmail();
                                commit();
                                showNotification("Коммерческое предложение отправлено", NotificationType.TRAY);
                            }
                        },
                        new DialogAction(DialogAction.Type.NO)
                }
        );
    }

    private void sendByEmail() {
        boolean res;
        try {
            res = ticketService.sendEmail(getItem());
            if (!res)
                throw new IOException();
            Comment comment = metadata.create(Comment.class);
            comment.setTicket(getItem());
            comment.setDateTime(new Date());
            comment.setUser(userSessionSource.getUserSession().getUser());
            comment.setComment("Отправлено коммерческое предложение");
            historyDs.addItem(comment);
        } catch (IOException e) {
            showNotification("Не удалось отправить письмо!", NotificationType.ERROR);
        }
    }

    @SuppressWarnings("Duplicates")
    public Component generateCommentLine(Comment entity) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String commentText = String.format("<b>%s %s написал(а):</b> \n%s", formatter.format(entity.getDateTime()), entity.getUser().getName(), entity.getComment()).replaceAll("\n", "<br>");
        Label label = componentsFactory.createComponent(Label.class);
        label.setValue(commentText);
        label.addStyleName("commentLabel");
        label.setHtmlEnabled(true);
        return label;
    }

    public void onAddCommentButtonClick() {
        if (newCommentText.getValue() == null)
            return;
        Comment comment = metadata.create(Comment.class);
        comment.setDateTime(new Date());
        User currentUser = userSession.getUser();
        if (currentUser == null) {
            showNotification("Ошибка при добавлении комментария", "Пользователь не определен", NotificationType.ERROR);
            return;
        }
        comment.setUser(currentUser);
        comment.setComment(newCommentText.getValue());
        comment.setTicket(getItem());
        historyDs.addItem(comment);
        newCommentText.setValue(null);
    }
}