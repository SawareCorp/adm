package com.company.adm.web.contractserviceline;

import com.company.adm.entity.Direction;
import com.company.adm.entity.contracts.Service;
import com.company.adm.entity.contracts.analytics.Analytics;
import com.company.adm.entity.contracts.analytics.SuitableBank;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.adm.entity.contracts.ContractServiceLine;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class ContractServiceLineEdit extends AbstractEditor<ContractServiceLine> {
    @Named("contractServiceLineFieldGroup.service")
    private LookupPickerField serviceField;
    @Inject
    private CollectionDatasource<Service, UUID> servicesDs;
    private Direction contractDirection;
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
        contractDirection = (Direction) params.get("DIRECTION");
    }

    @Override
    protected void postInit() {
        servicesDs.refresh(ParamsMap.of("direction", contractDirection));
        serviceField.addValueChangeListener(valueChangeEvent -> {
            ContractServiceLine serviceLine = getItem();
            Service service = servicesDs.getItem();
            if(service == null)
                serviceLine.setCost(null);
            else serviceLine.setCost(service.getDefaultTax());
        });
    }
}