package com.company.adm.entity.contracts.analytics;

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
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@NamePattern("%s|name")
@Table(name = "ADM_DOCUMENT")
@Entity(name = "adm$Document")
public class Document extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = 5977985840590720200L;

    @Column(name = "NAME", nullable = false)
    protected String name;

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

    @JoinTable(name = "ADM_SUITABLE_BANK_DOCUMENT_LINK",
        joinColumns = @JoinColumn(name = "DOCUMENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "SUITABLE_BANK_ID"))
    @ManyToMany
    protected List<SuitableBank> suitableBanks;

    public void setSuitableBanks(List<SuitableBank> suitableBanks) {
        this.suitableBanks = suitableBanks;
    }

    public List<SuitableBank> getSuitableBanks() {
        return suitableBanks;
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