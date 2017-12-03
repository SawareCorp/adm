package com.company.adm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.company.adm.service.TicketService;
import com.haulmont.chile.core.annotations.Composition;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import javax.persistence.Version;
import com.haulmont.cuba.core.entity.Updatable;
import java.util.Date;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.global.AppBeans;
import com.company.adm.entity.contracts.analytics.Analytics;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|name")
@Table(name = "ADM_BANK")
@Entity(name = "adm$Bank")
public class Bank extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = 8466131065059078572L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "ADDRESS")
    protected String address;

    @Composition
    @OneToMany(mappedBy = "bank")
    protected List<Contact> contacts;

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




    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
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

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }


}