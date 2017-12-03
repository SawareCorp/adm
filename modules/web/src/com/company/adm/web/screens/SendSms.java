package com.company.adm.web.screens;

import com.company.adm.entity.SmsTemplate;
import com.company.adm.entity.Ticket;
import com.company.adm.service.TicketService;
import com.haulmont.cuba.core.app.PersistenceManagerService;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.ResizableTextArea;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

public class SendSms extends AbstractWindow {
    @Inject
    private ResizableTextArea messageField;
    @Inject
    private LookupField templateChooser;
    @Inject
    private TicketService ticketService;
    private Ticket inputTicket;

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
        if (params.get("TICKET") == null)
            close("error");
        else inputTicket = (Ticket) params.get("TICKET");
        templateChooser.addValueChangeListener(e -> {
            if (e.getValue() == null) {
                messageField.setValue(null);
                return;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            String dateString;
            if (inputTicket.getStatusDateTime() == null)
                dateString = "";
            else dateString = simpleDateFormat.format(inputTicket.getStatusDateTime());
            String message = String.format(((SmsTemplate) e.getValue()).getTemplateText(), dateString);
            messageField.setValue(message);
        });
    }

    public void onSendSmsButtonClick() {
        String smsText = messageField.getValue(),
                phoneNumber = inputTicket.getPhone();
        boolean result = ticketService.sendSms(phoneNumber, smsText);
        if (result) {
            ticketService.addSmsCommentToTicket(inputTicket, smsText, phoneNumber);
            close("success");
        } else close("error");
    }

    public void onCancelButtonClick() {
        close("close");
    }
}