package com.company.adm.web.contract;

import com.company.adm.entity.contracts.Contract;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.reports.gui.actions.TablePrintFormAction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class ContractBrowse extends AbstractLookup {
    @Inject
    private Button reportsButton;
    @Inject
    private GroupTable<Contract> contractsTable;
    @Named("contractsTable.remove")
    private RemoveAction contractsTableRemove;

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
        TablePrintFormAction action = new TablePrintFormAction("report", contractsTable);
        contractsTable.addAction(action);
        reportsButton.setAction(action);
    }

    /**
     * Hook to be implemented in subclasses. <br>
     * Called by the framework after the screen is fully initialized and opened. <br>
     * Override this method and put custom initialization logic here.
     */
    @Override
    public void ready() {
        contractsTableRemove.setBeforeActionPerformedHandler(() -> {
            Contract contract = contractsTable.getSingleSelected();
            if(contract.getAnalytics().size() > 0 ){
                showNotification("Удаление невозможно! Сначала удалите связанные аналитики", NotificationType.ERROR);
                return false;
            }else if(contract.getHistory().size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные комментарии", NotificationType.ERROR);
                return false;
            }else if(contract.getServices().size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные услуги", NotificationType.ERROR);
                return false;
            }else if(contract.getInternalFiles().size() > 0){
                showNotification("Удаление невозможно! Сначала удалите связанные файлы", NotificationType.ERROR);
                return false;
            }
            return true;
        });
    }
}