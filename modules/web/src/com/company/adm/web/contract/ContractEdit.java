package com.company.adm.web.contract;

import com.company.adm.entity.Face;
import com.company.adm.entity.Questionnaire;
import com.company.adm.entity.Ticket;
import com.company.adm.entity.contracts.*;
import com.company.adm.entity.contracts.analytics.Analytics;
import com.company.adm.entity.contracts.analytics.BankTicketLine;
import com.company.adm.entity.scheduler.CustomScheduler;
import com.company.adm.service.AdmConfig;
import com.company.adm.service.ContractService;
import com.company.adm.service.UniqueNumbersExtService;
import com.company.adm.web.contractor.ContractorEdit;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.gui.components.WebMaskedField;
import com.haulmont.reports.gui.actions.EditorPrintFormAction;
import com.haulmont.reports.gui.actions.TablePrintFormAction;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ContractEdit extends AbstractEditor<Contract> {
    @Inject
    private CollectionDatasource<ContractServiceLine, UUID> servicesDs;
    @Named("fieldGroup.contractor")
    private LookupPickerField contractorField;
    @Named("fieldGroup.direction")
    private LookupField directionField;
    @Named("contractorFieldGroup.face")
    private LookupField faceField;
    @Named("contractorFieldGroup.dateBorn")
    private DateField dateBornField;
    @Named("contractorFieldGroup.passport")
    private WebMaskedField passportField;
    @Named("contractorFieldGroup.issuingAuthority")
    private TextField issuingAuthorityField;
    @Named("contractorFieldGroup.issueDate")
    private DateField issueDateField;
    @Named("contractorFieldGroup.divisionCode")
    private WebMaskedField divisionCodeField;
    @Named("contractorFieldGroup.snails")
    private WebMaskedField snailsField;
    @Named("contractorFieldGroup.address")
    private ResizableTextArea addressField;
    @Named("contractorFieldGroup.tin")
    private TextField tinField;
    @Named("contractorFieldGroup.kpp")
    private TextField kppField;
    @Named("contractorFieldGroup.ogrn")
    private TextField ogrnField;
    @Named("contractorFieldGroup.juridicalAddress")
    private TextField juridicalAddressField;
    @Named("contractorFieldGroup.postAddress")
    private TextField postAddressField;
    @Named("fieldGroup.ticket")
    private PickerField ticketField;
    @Named("contractorFieldGroup.representative")
    private TextField representativeField;
    @Inject
    private FieldGroup contractorFieldGroup;
    @Inject
    private VBoxLayout newContractorVbox;
    @Inject
    private DateField extDateBornField;
    @Inject
    private TextField extNameField;
    @Inject
    private LookupField extFaceField;
    @Inject
    private TextField extTinField;
    @Inject
    private Button createContractor;
    @Inject
    private CollectionDatasource<Contractor, UUID> contractorsDs;
    @Inject
    private Datasource<Contract> contractDs;
    @Inject
    private ContractService contractService;
    @Inject
    private Button schedulerButton;
    @Inject
    private Button reportAnalytic;
    @Inject
    private Table<Analytics> analyticsTable;
    @Inject
    private FileMultiUploadField contractMultiUpload;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private ExportDisplay exportDisplay;
    @Inject
    private CollectionDatasource<FileDescriptor, UUID> internalFilesDs;
    @Named("servicesTable.create")
    private CreateAction servicesTableCreate;
    @Inject
    private Button reportsButton;
    @Inject
    private AdmConfig admConfig;
    @Inject
    private Table<FileDescriptor> internalFilesTable;
    @Inject
    private Metadata metadata;
    @Inject
    private CollectionDatasource<CustomScheduler, UUID> customSchedulerDs;
    @Inject
    private CollectionDatasource<Comment, UUID> historyDs;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private ComponentsFactory componentsFactory;
    @Inject
    private ResizableTextArea newCommentText;
    @Inject
    private UserSession userSession;
    @Inject
    private Datasource<Questionnaire> questionnaireDs;
    @Named("fieldGroup.desiredAmount")
    private TextField desiredAmountField;
    private Ticket sourceTicket;


    @Override
    public void init(Map<String, Object> params) {
        sourceTicket = (Ticket) params.get("TICKET");
        faceField.addValueChangeListener(e -> {
            if (e.getValue() == Face.individual) {
                dateBornField.setVisible(true);
                passportField.setVisible(true);
                issuingAuthorityField.setVisible(true);
                issueDateField.setVisible(true);
                divisionCodeField.setVisible(true);
                snailsField.setVisible(true);
                addressField.setVisible(true);
                tinField.setVisible(false);
                kppField.setVisible(false);
                ogrnField.setVisible(false);
                juridicalAddressField.setVisible(false);
                postAddressField.setVisible(false);
            } else if (e.getValue() == Face.entity) {
                dateBornField.setVisible(false);
                passportField.setVisible(false);
                issuingAuthorityField.setVisible(false);
                issueDateField.setVisible(false);
                divisionCodeField.setVisible(false);
                snailsField.setVisible(false);
                addressField.setVisible(false);
                tinField.setVisible(true);
                kppField.setVisible(true);
                ogrnField.setVisible(true);
                juridicalAddressField.setVisible(true);
                postAddressField.setVisible(true);
            }
        });
        prepareExtGroup();

        contractMultiUpload.addQueueUploadCompleteListener(() -> {
            for (Map.Entry<UUID, String> entry : contractMultiUpload.getUploadsMap().entrySet()) {
                UUID fileId = entry.getKey();
                String fileName = entry.getValue();
                FileDescriptor fd = fileUploadingAPI.getFileDescriptor(fileId, fileName);
                // save file to FileStorage
                try {
                    fileUploadingAPI.putFileIntoStorage(fileId, fd);
                } catch (FileStorageException e) {
                    new RuntimeException("Error saving file to FileStorage!", e);
                }
                // save file descriptor to database
                internalFilesDs.addItem(fd);
            }
            showNotification("Добавлен файл: " + contractMultiUpload.getUploadsMap().values(), NotificationType.HUMANIZED);
            contractMultiUpload.clearUploads();
        });
        contractMultiUpload.addFileUploadErrorListener(event ->
                showNotification("File upload error", NotificationType.HUMANIZED));


        TablePrintFormAction action = new TablePrintFormAction("report", analyticsTable);
        action.setBeforeActionPerformedHandler(() -> {
            Analytics analytics = (Analytics) analyticsTable.getDatasource().getItem();
            if (analytics != null && analytics.isNew()) {
                showNotification("Перед печатью документа необходимо сохранить договор", NotificationType.HUMANIZED);
                return false;
            } else if (analytics == null) {
                showNotification("Выберите документ для печати", NotificationType.HUMANIZED);
                return false;
            } else return true;
        });
        analyticsTable.addAction(action);
        reportAnalytic.setAction(action);
        reportAnalytic.setUseResponsePending(true);

        contractorField.addValueChangeListener(e -> {
            if (getItem().getQuestionnaire() != null) {
                questionnaireDs.getItem().setName(getItem().getContractor().getName());
                questionnaireDs.getItem().setPhone(getItem().getContractor().getPhone());
            }
        });
    }

    private void prepareExtGroup() {
        List<Face> faceList = new ArrayList<>();
        for (Face face : Face.values())
            faceList.add(face);
        extFaceField.setOptionsList(faceList);

        extVisibility(null);
        extFaceField.addValueChangeListener(e -> extVisibility(e));

        extNameField.addValueChangeListener(e -> {
            if (!checkExtValues())
                createContractor.setEnabled(false);
            else createContractor.setEnabled(true);
        });
        extTinField.addValueChangeListener(e -> {
            if (!checkExtValues())
                createContractor.setEnabled(false);
            else createContractor.setEnabled(true);
        });
        extDateBornField.addValueChangeListener(e -> {
            if (!checkExtValues())
                createContractor.setEnabled(false);
            else createContractor.setEnabled(true);
        });
    }

    private boolean checkExtValues() {
        if (extNameField.getValue() == null || extNameField.getValue().toString().length() == 0)
            return false;
        if (extFaceField.getValue() == null)
            return false;
        else if (extFaceField.getValue() == Face.individual) {
            if (extDateBornField.getValue() == null)
                return false;
        } else if (extFaceField.getValue() == Face.entity) {
            if (extTinField.getValue() == null || extTinField.getValue().toString().length() == 0)
                return false;
        }
        return true;
    }

    @Override
    protected void postInit() {
        Contract contract = getItem();
        if (contract.isNew())
            schedulerButton.setEnabled(false);

        representativeField.addValueChangeListener(e -> {
            Contractor contractorByName = contractService.findContractorByName((String) e.getValue());
            if (contractorByName != null) {
                showNotification("Найден предполагаемый контрагент-представитель<br>" +
                        "<a href=\"/adm/open?screen=adm$Contractor.edit&item=adm$Contractor-" + contractorByName.getId().toString() + "\" target = \"_blank\">" + contractorByName.getName() + "</a>", NotificationType.HUMANIZED_HTML);
            }
        });
        reportsButton.setAction(new EditorPrintFormAction("report", this, null));
        ((EditorPrintFormAction) reportsButton.getAction()).setBeforeActionPerformedHandler(() -> commit());

        servicesTableCreate.setBeforeActionPerformedHandler(() -> {
            if (AppBeans.get(ContractService.class).getDefaultServiceStatus() == null) {
                showNotification("Не найден статус услуги по умолчанию. Создайте статус с порядковым номером " + admConfig.getDefaultServiceStatusSerialNumber());
                return false;
            }
            servicesTableCreate.setWindowParams(ParamsMap.of("DIRECTION", directionField.getValue()));
            return true;
        });

        ticketField.addValueChangeListener(e -> {
            contract.setQuestionnaire(((Ticket) e.getValue()).getQuestionnaire());
            if (((Ticket) e.getValue()).getQuestionnaire() != null)
                extDateBornField.setValue(((Ticket) e.getValue()).getQuestionnaire().getDateBorn());
        });
        if (contract.getTicket() != null)
            ticketField.setEditable(false);

        addCloseListener(actionId -> {
            if (actionId.equals("windowClose")) {
                CollectionDatasource<ContractServiceLine, UUID> servicesDs = (CollectionDatasource) contractDs.getDsContext().get("servicesDs");
                servicesDs.invalidate();
                servicesDs.getItem();
            }
        });

        if (contract.getCreateDate() == null) {
            contract.setContractStatus(ContractStatus.Active);
            contract.setCreateDate(new Date());
        }
        servicesDs.addCollectionChangeListener(collectionChangeEvent -> calculateAmount());
        servicesDs.addItemPropertyChangeListener(e -> calculateAmount());
        desiredAmountField.addValueChangeListener(e -> calculateAmount());
        contractorField.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                newContractorVbox.setVisible(false);
                newContractorVbox.setEnabled(false);
                contractorFieldGroup.setVisible(true);
            }
        });

        if (contract.getCreateTs() == null)
            contractorFieldGroup.setVisible(false);
        else if (contract.getCreateTs() != null)
            newContractorVbox.setVisible(false);

        //Генерация следующего контракта
        if (contract.getCreateTs() == null)
            contract.setContractNumber(contractService.getNextContractNumber());

        if (sourceTicket != null) {
            contract.setTicket(sourceTicket);
            contract.setQuestionnaire(sourceTicket.getQuestionnaire());
            ticketField.setEditable(false);
            extNameField.setValue(sourceTicket.getName());
            extFaceField.setValue(sourceTicket.getFace());
            contract.setResponsibleManager(sourceTicket.getResponsibleManager());
            contract.setSource(sourceTicket.getSourceDescription());
            contract.setDesiredAmount(sourceTicket.getAmount());
        }
        if (contract.getQuestionnaire() == null) {
            Questionnaire questionnaire = metadata.create(Questionnaire.class);
            if (sourceTicket != null)
                questionnaire.setPhone(sourceTicket.getPhone());
            contract.setQuestionnaire(questionnaire);
        }

        //Блокировать изменение направления договора при существующих услугах
        CollectionDatasource<ContractServiceLine, UUID> services = (CollectionDatasource<ContractServiceLine, UUID>) contractDs.getDsContext().get("servicesDs");
        if (services.size() > 0)
            directionField.setEditable(false);

        services.addCollectionChangeListener(e -> {
            Contract currentItem = contract;
            if (currentItem.getServices() != null && currentItem.getServices().size() > 0) {
                directionField.setEditable(false);
            } else directionField.setEditable(true);
        });
    }

    @Override
    protected boolean preCommit() {
        getItem().setContractor(contractorsDs.getItem());
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (getItem().getSchedules() != null) {
            for (CustomScheduler scheduler : getItem().getSchedules()) {
                if (PersistenceHelper.isNew(scheduler)) {
                    customSchedulerDs.addItem(scheduler);
                    Comment comment = metadata.create(Comment.class);
                    comment.setComment(String.format("Задача: %s, %s", format.format(scheduler.getDate()), scheduler.getComment()));
                    comment.setUser(userSessionSource.getUserSession().getUser());
                    comment.setDateTime(new Date());
                    comment.setContract(getItem());
                    historyDs.addItem(comment);
                }
            }
        }
        return true;
    }

    /**
     * Hook to be implemented in subclasses. Called by the framework after committing datasources.
     * The default implementation notifies about commit and calls {@link #postInit()} if the window is not closing.
     *
     * @param committed whether any data were actually changed and committed
     * @param close     whether the window is going to be closed
     * @return true to continue, false to abort
     */
    @Override
    protected boolean postCommit(boolean committed, boolean close) {
        if (!schedulerButton.isEnabled())
            schedulerButton.setEnabled(true);
        return super.postCommit(committed, close);
    }

    private void calculateAmount() {
        long amount = 0;
        for (ContractServiceLine line : servicesDs.getItems()) {
            try {
                amount += Long.valueOf(line.getCost());
            } catch (NumberFormatException e) {
            }
        }

        try {
            Integer tempDesire = Integer.valueOf(getItem().getDesiredAmount());
            if (tempDesire < 1100000)
                amount += tempDesire * 0.1;
            else if (tempDesire < 2000001)
                amount += tempDesire * 0.09;
            else if (tempDesire < 4000001)
                amount += tempDesire * 0.08;
            else if (tempDesire < 6000001)
                amount += tempDesire * 0.07;
            else if (tempDesire < 8000001)
                amount += tempDesire * 0.06;
            else if (tempDesire < 12000000)
                amount += tempDesire * 0.05;
            else if (tempDesire < 16000000)
                amount += tempDesire * 0.04;
            else if (tempDesire >= 16000000)
                amount += tempDesire * 0.03;


        } catch (Exception e) {


        }

        getItem().setAmount(amount);
    }

    private Contractor createContractor() {
        Contractor contractor = metadata.create(Contractor.class);
        contractor.setName(extNameField.getValue());
        if (extFaceField.getValue() == Face.individual)
            contractor.setDateBorn(extDateBornField.getValue());
        else if (extFaceField.getValue() == Face.entity)
            contractor.setTin(extTinField.getValue());
        if (sourceTicket != null) {
            contractor.setFace(sourceTicket.getFace());
            contractor.setPhone(sourceTicket.getPhone());
            contractor.setEmail(sourceTicket.getEMail());
        } else contractor.setFace(extFaceField.getValue());
        contractorsDs.addItem(contractor);
        contractorsDs.setItem(contractor);
        try {
            contractorsDs.commit();
        } catch (RemoteException e) {
            for (RemoteException.Cause cause : e.getCauses()) {
                if (cause.getClassName().equals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")) {
                    showNotification("Такой контрагент уже существует. Воспользуйтесь поиском", NotificationType.ERROR);
                    contractorsDs.removeItem(contractor);
                    break;
                }
            }
            return null;
        }
        return contractor;
    }

    public void onCreateContractorClick() {
        Contractor contractor = createContractor();
        if (contractor != null) {
            getItem().setContractor(contractor);
            newContractorVbox.setVisible(false);
            newContractorVbox.setEnabled(false);
            contractorFieldGroup.setVisible(true);
        }
    }

    private void extVisibility(ValueChangeEvent e) {
        if (e == null) {
            extDateBornField.setVisible(false);
            extTinField.setVisible(false);
            createContractor.setEnabled(false);
            return;
        } else if (e.getValue() == Face.individual) {
            extDateBornField.setVisible(true);
            extTinField.setVisible(false);
            createContractor.setEnabled(true);
        } else if (e.getValue() == Face.entity) {
            extDateBornField.setVisible(false);
            extTinField.setVisible(true);
            createContractor.setEnabled(true);
        } else {
            extDateBornField.setVisible(false);
            extTinField.setVisible(false);
            createContractor.setEnabled(false);
            return;
        }
        if (!checkExtValues())
            createContractor.setEnabled(false);
        else createContractor.setEnabled(true);
    }

    public void onSchedulerButtonClick() {
        Map<String, Object> params = new HashMap<>();
        params.put("ITEM", getItem());
        openWindow("scheduler-add-screen", WindowManager.OpenType.DIALOG, params);
    }

    public void onReportAnalyticClick() {
        analyticsTable.getDatasource().commit();
    }

    public void onEdit(Component source) {
        Collection<FileDescriptor> downloadedFiles = internalFilesTable.getSelected();
        for (FileDescriptor fileDescriptor : downloadedFiles)
            exportDisplay.show(fileDescriptor);
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
        comment.setContract(getItem());
        historyDs.addItem(comment);
        newCommentText.setValue(null);
    }
}