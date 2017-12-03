package com.company.adm.web.ticketstate;

import com.company.adm.entity.TicketState;
import com.company.adm.service.TicketService;
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

public class TicketStateBrowse extends AbstractLookup {

    /**
     * The {@link CollectionDatasource} instance that loads a list of {@link TicketState} records
     * to be displayed in {@link TicketStateBrowse#ticketStatesTable} on the left
     */
    @Inject
    private CollectionDatasource<TicketState, UUID> ticketStatesDs;

    /**
     * The {@link Datasource} instance that contains an instance of the selected entity
     * in {@link TicketStateBrowse#ticketStatesDs}
     * <p/> Containing instance is loaded in {@link CollectionDatasource#addItemChangeListener}
     * with the view, specified in the XML screen descriptor.
     * The listener is set in the {@link TicketStateBrowse#init(Map)} method
     */
    @Inject
    private Datasource<TicketState> ticketStateDs;

    /**
     * The {@link Table} instance, containing a list of {@link TicketState} records,
     * loaded via {@link TicketStateBrowse#ticketStatesDs}
     */
    @Inject
    private Table<TicketState> ticketStatesTable;

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
     * The {@link FieldGroup} instance that is linked to {@link TicketStateBrowse#ticketStateDs}
     * and shows fields of the selected {@link TicketState} record
     */
    @Inject
    private FieldGroup fieldGroup;

    /**
     * The {@link RemoveAction} instance, related to {@link TicketStateBrowse#ticketStatesTable}
     */
    @Named("ticketStatesTable.remove")
    private RemoveAction ticketStatesTableRemove;

    @Inject
    private DataSupplier dataSupplier;
    @Inject
    private TicketService ticketService;
    /**
     * {@link Boolean} value, indicating if a new instance of {@link TicketState} is being created
     */
    private boolean creating;

    @Override
    public void init(Map<String, Object> params) {

        /*
         * Adding {@link com.haulmont.cuba.gui.data.Datasource.ItemChangeListener} to {@link ticketStatesDs}
         * The listener reloads the selected record with the specified view and sets it to {@link ticketStateDs}
         */
        ticketStatesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                TicketState reloadedItem = dataSupplier.reload(e.getDs().getItem(), ticketStateDs.getView());
                ticketStateDs.setItem(reloadedItem);
            }
        });

        /*
         * Adding {@link CreateAction} to {@link ticketStatesTable}
         * The listener removes selection in {@link ticketStatesTable}, sets a newly created item to {@link ticketStateDs}
         * and enables controls for record editing
         */
        ticketStatesTable.addAction(new CreateAction(ticketStatesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity newItem, Datasource parentDs, Map<String, Object> params) {
                ticketStatesTable.setSelected(Collections.emptyList());
                ticketStateDs.setItem((TicketState) newItem);
                refreshOptionsForLookupFields();
                enableEditControls(true);
            }
        });

        /*
         * Adding {@link EditAction} to {@link ticketStatesTable}
         * The listener enables controls for record editing
         */
        ticketStatesTable.addAction(new EditAction(ticketStatesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity existingItem, Datasource parentDs, Map<String, Object> params) {
                if (ticketStatesTable.getSelected().size() == 1) {
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
         * Setting {@link RemoveAction#afterRemoveHandler} for {@link ticketStatesTableRemove}
         * to reset record, contained in {@link ticketStateDs}
         */
        ticketStatesTableRemove.setAfterRemoveHandler(removedItems -> ticketStateDs.setItem(null));

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

        TicketState editedItem = ticketStateDs.getItem();
        if (creating) {
            ticketStatesDs.includeItem(editedItem);
        } else {
            ticketStatesDs.updateItem(editedItem);
        }
        ticketStatesTable.setSelected(editedItem);

        disableEditControls();
    }

    /**
     * Method that is invoked by clicking Cancel button, discards changes and disables controls for record editing
     */
    public void cancel() {
        TicketState selectedItem = ticketStatesDs.getItem();
        if (selectedItem != null) {
            TicketState reloadedItem = dataSupplier.reload(selectedItem, ticketStateDs.getView());
            ticketStatesDs.setItem(reloadedItem);
        } else {
            ticketStateDs.setItem(null);
        }

        disableEditControls();
    }

    /**
     * Enabling controls for record editing
     *
     * @param creating indicates if a new instance of {@link TicketState} is being created
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
        ticketStatesTable.requestFocus();
    }

    /**
     * Initiating edit controls, depending on if they should be enabled/disabled
     *
     * @param enabled if true - enables editing controls and disables controls on the left side of the splitter
     *                if false - visa versa
     */
    private void initEditComponents(boolean enabled) {
        fieldGroup.setEditable(enabled);
        actionsPane.setVisible(enabled);
        lookupBox.setEnabled(!enabled);
    }

    public void onSetDefault(Component source) {
        ticketService.setDefaultTicketStatus(ticketStatesDs.getItem());
        ticketStatesDs.refresh();
    }

    public void onSetClosed(Component source) {
        ticketService.setClosedTicketStatus(ticketStatesDs.getItem());
        ticketStatesDs.refresh();
    }
}