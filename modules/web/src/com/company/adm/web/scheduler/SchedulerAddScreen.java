package com.company.adm.web.scheduler;

import com.company.adm.entity.Ticket;
import com.company.adm.entity.contracts.Contract;
import com.company.adm.entity.scheduler.CustomScheduler;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.ResizableTextArea;

import javax.inject.Inject;
import java.util.Map;

public class SchedulerAddScreen extends AbstractWindow {
    @Inject
    private DateField dateField;
    @Inject
    private ResizableTextArea textArea;
    private Ticket ticket;
    private Contract contract;
    @Inject
    private Metadata metadata;

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
        Object input = params.get("ITEM");
        if(input instanceof Ticket)
            ticket = (Ticket) input;
        else if(input instanceof Contract)
            contract = (Contract) input;
    }

    public void onOkButtonClick() {
        CustomScheduler scheduler = metadata.create(CustomScheduler.class);
        if(dateField.getValue() == null){
            showNotification("Выберите дату", NotificationType.ERROR);
            return;
        }
        scheduler.setDate(dateField.getValue());
        scheduler.setComment(textArea.getValue());
        if(ticket != null) {
            scheduler.setTicket(ticket);
            ticket.getCustomScheduler().add(scheduler);
        }
        else if(contract != null) {
            scheduler.setContract(contract);
            contract.getSchedules().add(scheduler);
        }
        close(null);
    }
}