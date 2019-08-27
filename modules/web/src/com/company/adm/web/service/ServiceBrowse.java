package com.company.adm.web.service;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.EditAction;

import javax.inject.Named;
import java.util.Map;

public class ServiceBrowse extends AbstractLookup {
    @Named("servicesTable.edit")
    private EditAction servicesTableEdit;

    @Override
    public void init(Map<String, Object> params) {

    }
}