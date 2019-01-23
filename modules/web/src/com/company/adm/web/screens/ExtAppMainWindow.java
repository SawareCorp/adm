package com.company.adm.web.screens;

import com.company.crreps.service.CreditReportsService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractMainWindow;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.mainwindow.FtsField;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.config.WindowInfo;
import com.haulmont.cuba.web.WebWindowManager;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import com.haulmont.cuba.gui.components.Timer;

public class ExtAppMainWindow extends AbstractMainWindow {
    @Inject
    private FtsField ftsField;

    @Inject
    private Embedded logoImage;

    @Inject
    private SideMenu sideMenu;
    @Inject
    private CreditReportsService creditReportsService;
    private WebWindowManager windowManager = AppBeans.get(WebWindowManager.class);

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        sideMenu.requestFocus();

        initLayoutAnalyzerContextMenu(logoImage);
        initLogoImage(logoImage);
        initFtsField(ftsField);
    }

    /**
     * Hook to be implemented in subclasses. <br>
     * Called by the framework after the screen is fully initialized and opened. <br>
     * Override this method and put custom initialization logic here.
     */
    @Override
    public void ready() {
        openWindow("Scheduler", WindowManager.OpenType.NEW_TAB);
        onUpdateBalance(null);
    }

    public void onUpdateBalance(Timer source) {
        double accountBalance = creditReportsService.getAccountBalance();
        SideMenu.MenuItem crrepsMenuItem = sideMenu.getMenuItemNN("application-crreps");
        String balanceText = String.format("%s руб", accountBalance);
        crrepsMenuItem.setBadgeText(balanceText);
    }
}