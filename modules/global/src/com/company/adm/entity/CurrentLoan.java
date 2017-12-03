package com.company.adm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import javax.persistence.Version;
import com.haulmont.cuba.core.entity.Updatable;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.company.adm.entity.contracts.analytics.Analytics;

@NamePattern("%s %s|organization,creditSumm")
@Table(name = "ADM_CURRENT_LOAN")
@Entity(name = "adm$CurrentLoan")
public class CurrentLoan extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = 531187616887038869L;

    @Column(name = "ORGANIZATION", nullable = false)
    protected String organization;

    @Column(name = "CREDIT_SUMM")
    protected BigDecimal creditSumm;

    @Temporal(TemporalType.DATE)
    @Column(name = "APPLICATION_DATE")
    protected Date applicationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_LAST_PAYMENT")
    protected Date dateOfLastPayment;

    @Column(name = "BALANCE_OWED")
    protected BigDecimal balanceOwed;

    @Column(name = "MONTHLY_PAYMENT")
    protected Long monthlyPayment;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANALYTICS_ID")
    protected Analytics analytics;

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public Analytics getAnalytics() {
        return analytics;
    }


    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }


    public void setBalanceOwed(BigDecimal balanceOwed) {
        this.balanceOwed = balanceOwed;
    }

    public BigDecimal getBalanceOwed() {
        return balanceOwed;
    }

    public void setMonthlyPayment(Long monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Long getMonthlyPayment() {
        return monthlyPayment;
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


    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }

    public void setCreditSumm(BigDecimal creditSumm) {
        this.creditSumm = creditSumm;
    }

    public BigDecimal getCreditSumm() {
        return creditSumm;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setDateOfLastPayment(Date dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public Date getDateOfLastPayment() {
        return dateOfLastPayment;
    }


}