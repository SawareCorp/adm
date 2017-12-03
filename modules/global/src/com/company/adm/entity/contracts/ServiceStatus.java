package com.company.adm.entity.contracts;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import javax.persistence.Version;
import com.haulmont.cuba.core.entity.Updatable;
import java.util.Date;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.MetaProperty;
import javax.persistence.Transient;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import javax.validation.constraints.NotNull;

@Listeners("adm_ServiceStatusEntityListener")
@NamePattern("%s|name")
@Table(name = "ADM_SERVICE_STATUS")
@Entity(name = "adm$ServiceStatus")
public class ServiceStatus extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -4205628823579329478L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "IS_CREDITING")
    protected Boolean isCrediting;

    @Column(name = "IS_BOOKKEEPING")
    protected Boolean isBookkeeping;

    @Column(name = "IS_JURIDICAL")
    protected Boolean isJuridical;

    @Column(name = "DIRECTIONS_LIST")
    protected String directionsList;

    @NotNull
    @Column(name = "SERIAL_NUMBER", nullable = false, unique = true)
    protected Integer serialNumber;

    @Version
    @Column(name = "VERSION", nullable = false)
    protected Integer version;

    @Column(name = "UPDATE_TS")
    protected Date updateTs;

    @Column(name = "UPDATED_BY", length = 50)
    protected String updatedBy;

    @Column(name = "CREATE_TS")
    protected Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy;

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }


    public void setDirectionsList(String directionsList) {
        this.directionsList = directionsList;
    }

    public String getDirectionsList() {
        return directionsList;
    }


    public void setIsCrediting(Boolean isCrediting) {
        this.isCrediting = isCrediting;
    }

    public Boolean getIsCrediting() {
        return isCrediting;
    }

    public void setIsBookkeeping(Boolean isBookkeeping) {
        this.isBookkeeping = isBookkeeping;
    }

    public Boolean getIsBookkeeping() {
        return isBookkeeping;
    }

    public void setIsJuridical(Boolean isJuridical) {
        this.isJuridical = isJuridical;
    }

    public Boolean getIsJuridical() {
        return isJuridical;
    }


    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    @Override
    public Date getCreateTs() {
        return createTs;
    }


    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    @Override
    public Date getUpdateTs() {
        return updateTs;
    }


    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public Integer getVersion() {
        return version;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}