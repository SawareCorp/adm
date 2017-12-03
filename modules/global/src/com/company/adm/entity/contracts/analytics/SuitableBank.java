package com.company.adm.entity.contracts.analytics;

import javax.persistence.*;

import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import com.haulmont.cuba.core.entity.Updatable;
import java.util.Date;
import com.haulmont.cuba.core.entity.Creatable;
import com.company.adm.entity.Bank;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import java.util.List;

@Table(name = "ADM_SUITABLE_BANK")
@Entity(name = "adm$SuitableBank")
public class SuitableBank extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -479902209104641266L;

    @Lookup(type = LookupType.DROPDOWN, actions = {"open"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BANK_ID")
    protected Bank bank;

    @Column(name = "MAX_AMOUNT")
    protected Long maxAmount;

    @JoinTable(name = "ADM_SUITABLE_BANK_DOCUMENT_LINK",
        joinColumns = @JoinColumn(name = "SUITABLE_BANK_ID"),
        inverseJoinColumns = @JoinColumn(name = "DOCUMENT_ID"))
    @ManyToMany
    protected List<Document> documents;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STATE_ID")
    protected SuitableBankState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYTICS_ID")
    protected Analytics analytics;

    @Column(name = "COMMENT_")
    protected String comment;

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


    public void setState(SuitableBankState state) {
        this.state = state;
    }

    public SuitableBankState getState() {
        return state;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public Analytics getAnalytics() {
        return analytics;
    }



    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Document> getDocuments() {
        return documents;
    }


    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Long getMaxAmount() {
        return maxAmount;
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


}