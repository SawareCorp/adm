package com.company.adm.entity;

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
import javax.persistence.UniqueConstraint;

@NamePattern("%s|name")
@Table(name = "ADM_TICKET_STATE", uniqueConstraints = {
    @UniqueConstraint(name = "IDX_ADM_TICKET_STATE_UNQ_POSITION", columnNames = {"POSITION_"}),
    @UniqueConstraint(name = "IDX_ADM_TICKET_STATE_UNQ_NAME", columnNames = {"NAME"})
})
@Entity(name = "adm$TicketState")
public class TicketState extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = 4818032577402279090L;

    @Column(name = "NAME", nullable = false, unique = true)
    protected String name;

    @Column(name = "POSITION_", nullable = false, unique = true)
    protected Integer position;

    @Column(name = "HAS_DATE_TIME", nullable = false)
    protected Boolean hasDateTime = false;

    @Column(name = "DEFAULT_STATUS", nullable = false)
    protected Boolean defaultStatus = false;

    @Column(name = "CLOSED", nullable = false)
    protected Boolean closed = false;

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

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getClosed() {
        return closed;
    }


    public void setDefaultStatus(Boolean defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public Boolean getDefaultStatus() {
        return defaultStatus;
    }


    public void setHasDateTime(Boolean hasDateTime) {
        this.hasDateTime = hasDateTime;
    }

    public Boolean getHasDateTime() {
        return hasDateTime;
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

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }


}