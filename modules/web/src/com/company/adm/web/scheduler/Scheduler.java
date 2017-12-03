package com.company.adm.web.scheduler;

import com.company.adm.entity.Ticket;
import com.company.adm.entity.contracts.Contract;
import com.company.adm.entity.scheduler.CustomScheduler;
import com.company.adm.service.SchedulerService;
import com.haulmont.cuba.core.app.DataService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

import com.haulmont.cuba.security.entity.User;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;
import com.haulmont.reports.gui.actions.RunReportAction;

public class Scheduler extends AbstractWindow {
    @Inject
    private DateField dateField;
    @Inject
    private OptionsGroup managerField;
    @Inject
    private CollectionDatasource<Ticket, UUID> ticketCallsDs;
    @Inject
    private Table<CustomScheduler> customSchedulersContractTable;
    @Inject
    private CollectionDatasource<CustomScheduler, UUID> customSchedulersContractDs;
    @Inject
    private Table<CustomScheduler> customSchedulersTicketsTable;
    @Inject
    private CollectionDatasource<CustomScheduler, UUID> customSchedulersTicketsDs;
    @Inject
    private Button reportButton;
    @Inject
    private CollectionDatasource<Contract, UUID> contractsDs;
    @Inject
    private CollectionDatasource<User, UUID> usersDs;
    @Inject
    private DataService dataService;
    private ReportGuiManager reportGuiManager = AppBeans.get(ReportGuiManager.class);

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
        //reportButton.setAction(new RunReportAction("report"));

        dateField.addValueChangeListener(e -> refreshDataSources());
        managerField.addValueChangeListener(e -> refreshDataSources());
        customSchedulersTicketsDs.addItemPropertyChangeListener(e -> customSchedulersTicketsDs.commit());
        customSchedulersContractDs.addItemPropertyChangeListener(e -> customSchedulersContractDs.commit());
        usersDs.refresh();
        dateField.setValue(new Date());
        managerField.setValue(usersDs.getItems());
    }


    private void refreshDataSources() {
        ticketCallsDs.refresh();
        contractsDs.refresh();
        customSchedulersContractDs.refresh();
        customSchedulersTicketsDs.refresh();
    }

    public void onEdit(Component source) {
        if(source == customSchedulersTicketsTable)
            openEditor(customSchedulersTicketsDs.getItem().getTicket(), WindowManager.OpenType.THIS_TAB);
        else if(source == customSchedulersContractTable)
            openEditor(customSchedulersContractDs.getItem().getContract(), WindowManager.OpenType.THIS_TAB);
    }

    public void onRefreshButtonClick() {
        refreshDataSources();
    }

    public void onReportButtonClick() {
        Set<User> users = managerField.getValue();
        for (User user : users) {
            Map<String, Object> reportParams = new HashMap<>();
            reportParams.put("user", user);
            reportParams.put("date", dateField.getValue());

            LoadContext<Report> lContext = new LoadContext<>(Report.class);
            lContext.setQueryString("select r from report$Report r where r.code = 'SCHEDULER' ");

            Report report = dataService.load(lContext);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

            String fileName = String.format("План на %s для %s.doc", simpleDateFormat.format(dateField.getValue()), user.getName());
            reportGuiManager.printReport(report, reportParams, "DEFAULT", fileName);
        }
    }
}