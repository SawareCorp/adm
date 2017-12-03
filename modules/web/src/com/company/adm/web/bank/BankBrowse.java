package com.company.adm.web.bank;

import com.company.adm.entity.Bank;
import com.company.adm.entity.contracts.analytics.SuitableBank;
import com.company.adm.service.BankService;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.actions.RemoveAction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class BankBrowse extends AbstractLookup {
    @Inject
    private GroupTable<Bank> banksTable;
    @Named("banksTable.remove")
    private RemoveAction banksTableRemove;
    @Inject
    private BankService bankService;

    /**
     * Hook to be implemented in subclasses. <br>
     * Called by the framework after the screen is fully initialized and opened. <br>
     * Override this method and put custom initialization logic here.
     */
    @Override
    public void ready() {
        banksTableRemove.setBeforeActionPerformedHandler(()->{
            Bank bank = banksTable.getSingleSelected();
            if(bank.getContacts().size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные контакты", NotificationType.ERROR);
                return false;
            }
            List<SuitableBank> suitableBankList = bankService.getSuitableBanks(bank);
            if(suitableBankList != null && suitableBankList.size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные подходящие банки", NotificationType.ERROR);
                return false;
            }
            return true;
        });
    }
}