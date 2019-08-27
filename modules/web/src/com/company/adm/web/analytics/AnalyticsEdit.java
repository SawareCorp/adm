package com.company.adm.web.analytics;

import com.company.adm.entity.CurrentLoan;
import com.company.adm.entity.Questionnaire;
import com.company.adm.entity.contracts.Contract;
import com.company.adm.entity.contracts.analytics.BankTicketLine;
import com.company.adm.entity.contracts.analytics.SuitableBank;
import com.company.adm.service.ContractService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.UuidSource;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.company.adm.entity.contracts.analytics.Analytics;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.reports.gui.actions.EditorPrintFormAction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class AnalyticsEdit extends AbstractEditor<Analytics> {
    @Named("fieldGroup.dateCreation")
    private DateField dateCreationField;
    @Named("fieldGroup.number")
    private TextField numberField;
    @Inject
    private GroupBoxLayout bankTicketsBox;
    @Inject
    private GroupBoxLayout suitableBanksBox;
    @Inject
    private Button reportButton;
    @Inject
    private Table<SuitableBank> suitableBanksTable;
    @Named("fieldGroup.contract")
    private PickerField contractField;
    private ValueChangeListener listener = e -> {
        checkMandatoryFields();
    };

    /**
     * Hook to be implemented in subclasses. Called by {@link #setItem(Entity)}.
     * At the moment of calling the main datasource is initialized and {@link #getItem()} returns reloaded entity instance.
     * <br>
     * This method can be called second time by {@link #postCommit(boolean, boolean)} if the window is not closed after
     * commit. Then {@link #getItem()} contains instance, returned from {@code DataService.commit()}.
     * This is useful for initialization of components that have to show fresh information from the current instance.
     * <br>
     * Example:
     * <pre>
     * protected void postInit() {
     *     if (!PersistenceHelper.isNew(getItem())) {
     *        diffFrame.loadVersions(getItem());
     *        entityLogDs.refresh();
     *    }
     * }
     * </pre>
     */
    @Override
    protected void postInit() {
        contractField.addOpenAction();
        reportButton.setAction(new EditorPrintFormAction("report", this, null));
        ((EditorPrintFormAction) reportButton.getAction()).setBeforeActionPerformedHandler(() -> {
            if(getItem().isNew()){
                showNotification("Необходимо сохранить договор");
                return false;
            }else return true;
        });

        if(getItem().getDateCreation() == null){
            bankTicketsBox.setVisible(false);
            suitableBanksBox.setVisible(false);
            dateCreationField.addValueChangeListener(listener);
            numberField.addValueChangeListener(listener);
        }

        if(getItem().isNew()){
            Integer number = getNextAnalyticNumber(getItem().getContract().getAnalytics());
            getItem().setNumber(number);
            getItem().setDateCreation(new Date());
        }

        if((getItem().getCurrentLoans() != null && getItem().getCurrentLoans().size() > 0) || (getItem().getTicketToBanks() != null && getItem().getTicketToBanks().size() > 0)){
            importFromQuestionnair.setEnabled(false);
            importFromQuestionnair2.setEnabled(false);
        }
    }

    private void checkMandatoryFields(){
        if(dateCreationField.getValue() != null && numberField.getValue() != null){
            bankTicketsBox.setVisible(true);
            suitableBanksBox.setVisible(true);
            dateCreationField.removeValueChangeListener(listener);
            numberField.removeValueChangeListener(listener);
        }


    }

    /**
     * Hook to be implemented in subclasses. Called by the framework when all validation is done and datasources are
     * going to be committed.
     *
     * @return true to continue, false to abort
     */
    @Override
    protected boolean preCommit() {
        return super.preCommit();
    }

    private Integer getNextAnalyticNumber(List<Analytics> existsAnalytics){
        Integer nextNumber = 0;
        if(existsAnalytics == null)
            return 1;
        for (Analytics existsAnalytic : existsAnalytics) {
            if(existsAnalytic.getNumber() > nextNumber)
                nextNumber = existsAnalytic.getNumber();
        }
        return nextNumber + 1;
    }

    @Inject
    private DataManager dataManager;
    @Inject
    private Metadata metadata;

    @Inject
    private CollectionDatasource<CurrentLoan, UUID> analyticsCurrentLoansDs;
    @Inject
    private CollectionDatasource<BankTicketLine, UUID> ticketToBanksDs;
    @Inject
    private Button importFromQuestionnair;
    @Inject
    private Button importFromQuestionnair2;
    @Inject
    private UuidSource uuidSource;

    public void onImportFromQuestionnairClick() {
        Questionnaire questionnaire = getItem().getContract().getQuestionnaire();
        questionnaire = dataManager.reload(questionnaire, "questionnaire-view");

        Set<BankTicketLine> questionnaireBankTickets = questionnaire.getBankTickets();
        Set<CurrentLoan> questionnaireCurrentLoans = questionnaire.getCurrentLoans();

        for (BankTicketLine bankTicket : questionnaireBankTickets) {
            BankTicketLine deepCopy = metadata.getTools().deepCopy(bankTicket);
            deepCopy.setId(uuidSource.createUuid());
            deepCopy.setQuestionnaire(null);
            deepCopy.setAnalytics(getItem());
            ticketToBanksDs.addItem(deepCopy);
        }

        for (CurrentLoan currentLoan : questionnaireCurrentLoans) {
            CurrentLoan deepCopy = metadata.getTools().deepCopy(currentLoan);
            deepCopy.setId(uuidSource.createUuid());
            deepCopy.setQuestionnaire(null);
            deepCopy.setAnalytics(getItem());
            analyticsCurrentLoansDs.addItem(deepCopy);
        }

        importFromQuestionnair.setEnabled(false);
        importFromQuestionnair2.setEnabled(false);

    }
}