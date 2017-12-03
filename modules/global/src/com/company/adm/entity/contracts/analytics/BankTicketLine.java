package com.company.adm.entity.contracts.analytics;

import javax.persistence.*;

import com.company.adm.entity.Bank;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import java.util.Date;

import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import com.haulmont.cuba.core.entity.Updatable;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import com.company.adm.entity.Questionnaire;

@NamePattern("%s|bank")
@Table(name = "ADM_BANK_TICKET_LINE")
@Entity(name = "adm$BankTicketLine")
public class BankTicketLine extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -5887231208507852413L;

    @Lookup(type = LookupType.DROPDOWN, actions = {"open"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BANK_ID")
    protected Bank bank;

    @Temporal(TemporalType.DATE)
    @Column(name = "APPLICATION_DATE", nullable = false)
    protected Date applicationDate;

    @Column(name = "COMMENT_", length = 1000)
    protected String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYTICS_ID")
    protected Analytics analytics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIRE_ID")
    protected Questionnaire questionnaire;

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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }


    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public Analytics getAnalytics() {
        return analytics;
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


    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }


}