package com.company.adm.web.suitablebank;

import com.company.adm.entity.contracts.analytics.SuitableBank;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class SuitableBankBrowse extends AbstractLookup {
    @Inject
    private GroupDatasource<SuitableBank, UUID> suitableBanksDs;
    @Inject
    private GroupTable<SuitableBank> suitableBanksTable;

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
        suitableBanksDs.addItemPropertyChangeListener(e -> e.getDs().commit());
    }

    public void onEdit(Component source) {
        openEditor(suitableBanksDs.getItem().getAnalytics(), WindowManager.OpenType.THIS_TAB);
    }
}