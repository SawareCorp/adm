package com.company.adm.web.servicestatus;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.adm.entity.contracts.ServiceStatus;
import com.haulmont.cuba.gui.components.CheckBox;

import javax.inject.Named;

public class ServiceStatusEdit extends AbstractEditor<ServiceStatus> {
    @Named("fieldGroup.isBookkeeping")
    private CheckBox isBookkeepingField;
    @Named("fieldGroup.isCrediting")
    private CheckBox isCreditingField;
    @Named("fieldGroup.isJuridical")
    private CheckBox isJuridicalField;

    /**
     * Hook to be implemented in subclasses. Called by the framework when all validation is done and datasources are
     * going to be committed.
     *
     * @return true to continue, false to abort
     */
    @Override
    protected boolean preCommit() {
        ServiceStatus entity = getItem();
        StringBuilder builder = new StringBuilder();
        if (entity.getIsJuridical() != null && entity.getIsJuridical())
            builder.append(1);
        if (entity.getIsBookkeeping() != null && entity.getIsBookkeeping())
            builder.append(2);
        if (entity.getIsCrediting() != null && entity.getIsCrediting())
            builder.append(3);
        entity.setDirectionsList(builder.toString());
        return super.preCommit();
    }
}