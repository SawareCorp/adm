package com.company.adm.web.smstemplate;

import com.company.adm.entity.SmsTemplate;
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

public class SmsTemplateBrowse extends AbstractLookup {

    /**
     * The {@link CollectionDatasource} instance that loads a list of {@link SmsTemplate} records
     * to be displayed in {@link SmsTemplateBrowse#smsTemplatesTable} on the left
     */
    @Inject
    private CollectionDatasource<SmsTemplate, UUID> smsTemplatesDs;

    /**
     * The {@link Datasource} instance that contains an instance of the selected entity
     * in {@link SmsTemplateBrowse#smsTemplatesDs}
     * <p/> Containing instance is loaded in {@link CollectionDatasource#addItemChangeListener}
     * with the view, specified in the XML screen descriptor.
     * The listener is set in the {@link SmsTemplateBrowse#init(Map)} method
     */
    @Inject
    private Datasource<SmsTemplate> smsTemplateDs;

    /**
     * The {@link Table} instance, containing a list of {@link SmsTemplate} records,
     * loaded via {@link SmsTemplateBrowse#smsTemplatesDs}
     */
    @Inject
    private Table<SmsTemplate> smsTemplatesTable;

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
     * The {@link FieldGroup} instance that is linked to {@link SmsTemplateBrowse#smsTemplateDs}
     * and shows fields of the selected {@link SmsTemplate} record
     */
    @Inject
    private FieldGroup fieldGroup;

    /**
     * The {@link RemoveAction} instance, related to {@link SmsTemplateBrowse#smsTemplatesTable}
     */
    @Named("smsTemplatesTable.remove")
    private RemoveAction smsTemplatesTableRemove;

    @Inject
    private DataSupplier dataSupplier;

    /**
     * {@link Boolean} value, indicating if a new instance of {@link SmsTemplate} is being created
     */
    private boolean creating;

    @Override
    public void init(Map<String, Object> params) {

        /*
         * Adding {@link com.haulmont.cuba.gui.data.Datasource.ItemChangeListener} to {@link smsTemplatesDs}
         * The listener reloads the selected record with the specified view and sets it to {@link smsTemplateDs}
         */
        smsTemplatesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                SmsTemplate reloadedItem = dataSupplier.reload(e.getDs().getItem(), smsTemplateDs.getView());
                smsTemplateDs.setItem(reloadedItem);
            }
        });

        /*
         * Adding {@link CreateAction} to {@link smsTemplatesTable}
         * The listener removes selection in {@link smsTemplatesTable}, sets a newly created item to {@link smsTemplateDs}
         * and enables controls for record editing
         */
        smsTemplatesTable.addAction(new CreateAction(smsTemplatesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity newItem, Datasource parentDs, Map<String, Object> params) {
                smsTemplatesTable.setSelected(Collections.emptyList());
                smsTemplateDs.setItem((SmsTemplate) newItem);
                refreshOptionsForLookupFields();
                enableEditControls(true);
            }
        });

        /*
         * Adding {@link EditAction} to {@link smsTemplatesTable}
         * The listener enables controls for record editing
         */
        smsTemplatesTable.addAction(new EditAction(smsTemplatesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity existingItem, Datasource parentDs, Map<String, Object> params) {
                if (smsTemplatesTable.getSelected().size() == 1) {
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
         * Setting {@link RemoveAction#afterRemoveHandler} for {@link smsTemplatesTableRemove}
         * to reset record, contained in {@link smsTemplateDs}
         */
        smsTemplatesTableRemove.setAfterRemoveHandler(removedItems -> smsTemplateDs.setItem(null));

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

        SmsTemplate editedItem = smsTemplateDs.getItem();
        if (creating) {
            smsTemplatesDs.includeItem(editedItem);
        } else {
            smsTemplatesDs.updateItem(editedItem);
        }
        smsTemplatesTable.setSelected(editedItem);

        disableEditControls();
    }

    /**
     * Method that is invoked by clicking Cancel button, discards changes and disables controls for record editing
     */
    public void cancel() {
        SmsTemplate selectedItem = smsTemplatesDs.getItem();
        if (selectedItem != null) {
            SmsTemplate reloadedItem = dataSupplier.reload(selectedItem, smsTemplateDs.getView());
            smsTemplatesDs.setItem(reloadedItem);
        } else {
            smsTemplateDs.setItem(null);
        }

        disableEditControls();
    }

    /**
     * Enabling controls for record editing
     * @param creating indicates if a new instance of {@link SmsTemplate} is being created
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
        smsTemplatesTable.requestFocus();
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