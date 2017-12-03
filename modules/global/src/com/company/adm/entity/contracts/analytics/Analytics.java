package com.company.adm.entity.contracts.analytics;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.Composition;
import java.util.Date;
import java.util.List;

import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import com.haulmont.cuba.core.entity.Updatable;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import com.company.adm.entity.contracts.Service;
import com.company.adm.entity.contracts.ContractServiceLine;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.core.entity.annotation.Listeners;

import javax.validation.constraints.NotNull;
import com.company.adm.entity.Bank;
import com.company.adm.entity.contracts.Contract;
import com.company.adm.entity.CurrentLoan;

@Listeners("adm_AnalyticsEntityListener")
@NamePattern("%s от %s|number,dateCreation")
@Table(name = "ADM_ANALYTICS")
@Entity(name = "adm$Analytics")
public class Analytics extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -6669085546580402182L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false)
    protected Integer number;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATION", nullable = false)
    protected Date dateCreation;

    @Column(name = "SCORING_SCORE")
    protected Integer scoringScore;

    @Column(name = "SECURITY_CHECK", length = 1500)
    protected String securityCheck;

    @Column(name = "ARREARS")
    protected String arrears;

    @Column(name = "MONTHLY_PAYMENTS")
    protected Long monthlyPayments;

    @Column(name = "CURRENT_CREDITS")
    protected Long currentCredits;

    @Composition
    @OneToMany(mappedBy = "analytics")
    protected List<BankTicketLine> ticketToBanks;

    @Composition
    @OneToMany(mappedBy = "analytics")
    protected List<SuitableBank> suitableBanks;

    @Composition
    @OneToMany(mappedBy = "analytics")
    protected List<CurrentLoan> currentLoans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID")
    protected Contract contract;

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

    public void setCurrentLoans(List<CurrentLoan> currentLoans) {
        this.currentLoans = currentLoans;
    }

    public List<CurrentLoan> getCurrentLoans() {
        return currentLoans;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public List<SuitableBank> getSuitableBanks() {
        return suitableBanks;
    }

    public void setSuitableBanks(List<SuitableBank> suitableBanks) {
        this.suitableBanks = suitableBanks;
    }


    public List<BankTicketLine> getTicketToBanks() {
        return ticketToBanks;
    }

    public void setTicketToBanks(List<BankTicketLine> ticketToBanks) {
        this.ticketToBanks = ticketToBanks;
    }



    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }


    public String getArrears() {
        return arrears;
    }

    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    public void setMonthlyPayments(Long monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
    }

    public Long getMonthlyPayments() {
        return monthlyPayments;
    }

    public void setCurrentCredits(Long currentCredits) {
        this.currentCredits = currentCredits;
    }

    public Long getCurrentCredits() {
        return currentCredits;
    }



    public void setSecurityCheck(String securityCheck) {
        this.securityCheck = securityCheck;
    }

    public String getSecurityCheck() {
        return securityCheck;
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


    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setScoringScore(Integer scoringScore) {
        this.scoringScore = scoringScore;
    }

    public Integer getScoringScore() {
        return scoringScore;
    }

    public boolean isNew(){
        return __new;
    }
}