package com.company.adm.web.suitablebankstate;

import com.company.adm.entity.contracts.analytics.SuitableBankState;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.security.entity.EntityOp;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class SuitableBankStateBrowse extends AbstractLookup {

    /**
     * The {@link CollectionDatasource} instance that loads a list of {@link SuitableBankState} records
     * to be displayed in {@link SuitableBankStateBrowse#suitableBankStatesTable} on the left
     */
    @Inject
    private CollectionDatasource<SuitableBankState, UUID> suitableBankStatesDs;

    /**
     * The {@link Datasource} instance that contains an instance of the selected entity
     * in {@link SuitableBankStateBrowse#suitableBankStatesDs}
     * <p/> Containing instance is loaded in {@link CollectionDatasource#addItemChangeListener}
     * with the view, specified in the XML screen descriptor.
     * The listener is set in the {@link SuitableBankStateBrowse#init(Map)} method
     */
    @Inject
    private Datasource<SuitableBankState> suitableBankStateDs;

    /**
     * The {@link Table} instance, containing a list of {@link SuitableBankState} records,
     * loaded via {@link SuitableBankStateBrowse#suitableBankStatesDs}
     */
    @Inject
    private Table<SuitableBankState> suitableBankStatesTable;

    /**
     * The {@link BoxLayout} instance that contains components on the left side
     * of {@link SplitPanel}
     */
    @Inject
    private BoxLayout lookupBox;

    /**
    * The {@link BoxLayout} instance that contains components on the right side
    * of {@link SplitPanel}
    */
    @Inject
    private BoxLayout editBox;

    /**
     * The {@link BoxLayout} instance that contains buttons to invoke Save or Cancel actions in edit mode
     */
    @Inject
    private BoxLayout actionsPane;

    /**
     * The {@link FieldGroup} instance that is linked to {@link SuitableBankStateBrowse#suitableBankStateDs}
     * and shows fields of the selected {@link SuitableBankState} record
     */
    @Inject
    private FieldGroup fieldGroup;

    /**
     * The {@link RemoveAction} instance, related to {@link SuitableBankStateBrowse#suitableBankStatesTable}
     */
    @Named("suitableBankStatesTable.remove")
    private RemoveAction suitableBankStatesTableRemove;

    @Inject
    private DataSupplier dataSupplier;

    /**
     * {@link Boolean} value, indicating if a new instance of {@link SuitableBankState} is being created
     */
    private boolean creating;

    @Override
    public void init(Map<String, Object> params) {

        /*
         * Adding {@link com.haulmont.cuba.gui.data.Datasource.ItemChangeListener} to {@link suitableBankStatesDs}
         * The listener reloads the selected record with the specified view and sets it to {@link suitableBankStateDs}
         */
        suitableBankStatesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                SuitableBankState reloadedItem = dataSupplier.reload(e.getDs().getItem(), suitableBankStateDs.getView());
                suitableBankStateDs.setItem(reloadedItem);
            }
        });

        /*
         * Adding {@link CreateAction} to {@link suitableBankStatesTable}
         * The listener removes selection in {@link suitableBankStatesTable}, sets a newly created item to {@link suitableBankStateDs}
         * and enables controls for record editing
         */
        suitableBankStatesTable.addAction(new CreateAction(suitableBankStatesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity newItem, Datasource parentDs, Map<String, Object> params) {
                suitableBankStatesTable.setSelected(Collections.emptyList());
                suitableBankStateDs.setItem((SuitableBankState) newItem);
                refreshOptionsForLookupFields();
                enableEditControls(true);
            }
        });

        /*
         * Adding {@link EditAction} to {@link suitableBankStatesTable}
         * The listener enables controls for record editing
         */
        suitableBankStatesTable.addAction(new EditAction(suitableBankStatesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity existingItem, Datasource parentDs, Map<String, Object> params) {
                if (suitableBankStatesTable.getSelected().size() == 1) {
                    refreshOptionsForLookupFields();
                    enableEditControls(false);
                }
            }

            @Override
            public void refreshState() {
                if (target != null) {
                    CollectionDatasource ds = target.getDatasource();
                    if (ds != null && !captionInitialized) {
                        setCaption(messages.getMainMessage("actions.Edit"));
                    }
                }
                super.refreshState();
            }

            @Override
            protected boolean isPermitted() {
                CollectionDatasource ownerDatasource = target.getDatasource();
                boolean entityOpPermitted = security.isEntityOpPermitted(ownerDatasource.getMetaClass(), EntityOp.UPDATE);
                if (!entityOpPermitted) {
                    return false;
                }
                return super.isPermitted();
            }
        });

        /*
         * Setting {@link RemoveAction#afterRemoveHandler} for {@link suitableBankStatesTableRemove}
         * to reset record, contained in {@link suitableBankStateDs}
         */
        suitableBankStatesTableRemove.setAfterRemoveHandler(removedItems -> suitableBankStateDs.setItem(null));

        /*
         * Adding ESCAPE shortcut that invokes cancel() method
         */
        editBox.addShortcutAction(new ShortcutAction(new KeyCombination(KeyCombination.Key.ESCAPE),
        shortcutTriggeredEvent -> cancel()));

        disableEditControls();
    }

    private void refreshOptionsForLookupFields() {
        for (Component component : fieldGroup.getOwnComponents()) {
            if (component instanceof LookupField) {
                CollectionDatasource optionsDatasource = ((LookupField) component).getOptionsDatasource();
                if (optionsDatasource != null) {
                    optionsDatasource.refresh();
                }
            }
        }
    }

    /**
     * Method that is invoked by clicking Ok button after editing an existing or creating a new record
     */
    public void save() {
        if (!validate(Collections.singletonList(fieldGroup))) {
            return;
        }
        getDsContext().commit();

        SuitableBankState editedItem = suitableBankStateDs.getItem();
        if (creating) {
            suitableBankStatesDs.includeItem(editedItem);
        } else {
            suitableBankStatesDs.updateItem(editedItem);
        }
        suitableBankStatesTable.setSelected(editedItem);

        disableEditControls();
    }

    /**
     * Method that is invoked by clicking Cancel button, discards changes and disables controls for record editing
     */
    public void cancel() {
        SuitableBankState selectedItem = suitableBankStatesDs.getItem();
        if (selectedItem != null) {
            SuitableBankState reloadedItem = dataSupplier.reload(selectedItem, suitableBankStateDs.getView());
            suitableBankStatesDs.setItem(reloadedItem);
        } else {
            suitableBankStateDs.setItem(null);
        }

        disableEditControls();
    }

    /**
     * Enabling controls for record editing
     * @param creating indicates if a new instance of {@link SuitableBankState} is being created
     */
    private void enableEditControls(boolean creating) {
        this.creating = creating;
        initEditComponents(true);
        fieldGroup.requestFocus();
    }

    /**
     * Disabling editing controls
     */
    private void disableEditControls() {
        initEditComponents(false);
        suitableBankStatesTable.requestFocus();
    }

    /**
     * Initiating edit controls, depending on if they should be enabled/disabled
     * @param enabled if true - enables editing controls and disables controls on the left side of the splitter
     *                if false - visa versa
     */
    private void initEditComponents(boolean enabled) {
        fieldGroup.setEditable(enabled);
        actionsPane.setVisible(enabled);
        lookupBox.setEnabled(!enabled);
    }
}