package com.company.adm.web.contractor;

import com.company.adm.entity.contracts.Contractor;
import com.company.adm.service.ContractService;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.actions.RemoveAction;

import javax.inject.Inject;
import javax.inject.Named;

public class ContractorBrowse extends AbstractLookup {
    @Named("contractorsTable.remove")
    private RemoveAction contractorsTableRemove;
    @Inject
    private GroupTable<Contractor> contractorsTable;
    @Inject
    private ContractService contractService;

    /**
     * Hook to be implemented in subclasses. <br>
     * Called by the framework after the screen is fully initialized and opened. <br>
     * Override this method and put custom initialization logic here.
     */
    @Override
    public void ready() {
        contractorsTableRemove.setBeforeActionPerformedHandler(()->{
            Contractor contractor = contractorsTable.getSingleSelected();
            if(contractor.getContacts().size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные контакты", NotificationType.ERROR);
                return false;
            }else if(contractor.getFiles().size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные файлы", NotificationType.ERROR);
                return false;
            } else if(contractService.getContractsByContractor(contractor).size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные договоры", NotificationType.ERROR);
                return false;
            }
            return true;
        });
    }
}