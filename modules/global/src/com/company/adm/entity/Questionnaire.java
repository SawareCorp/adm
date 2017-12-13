package com.company.adm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import javax.persistence.Version;
import com.haulmont.cuba.core.entity.Updatable;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import com.company.adm.entity.contracts.analytics.BankTicketLine;
import com.haulmont.chile.core.annotations.Composition;
import java.util.List;
import javax.persistence.OneToMany;
import com.company.adm.entity.contracts.Contract;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Set;

@NamePattern("%s|name")
@Table(name = "ADM_QUESTIONNAIRE")
@Entity(name = "adm$Questionnaire")
public class Questionnaire extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -2862617889393595619L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_BORN")
    protected Date dateBorn;

    @NotNull
    @Column(name = "PHONE", nullable = false)
    protected String phone;

    @Column(name = "REGISTRATION_ADDRESS")
    protected String registrationAddress;

    @Column(name = "ACTUAL_ADDRESS")
    protected String actualAddress;

    @Column(name = "SECOND_DOCUMENT")
    protected String secondDocument;

    @Lob
    @Column(name = "PROPERTY_IN_OWN")
    protected String propertyInOwn;

    @Column(name = "ARREARS")
    protected String arrears;

    @Column(name = "PARTICIPATION_IN_LEGAL_ENTITIES")
    protected String participationInLegalEntities;

    @Column(name = "REGISTRATION_AS_IP")
    protected String registrationAsIp;

    @Column(name = "PROSECUTIONS")
    protected String prosecutions;

    @Column(name = "LITIGATIONS")
    protected String litigations;

    @Column(name = "DEBT_OF_BEILIFFS")
    protected String debtOfBeiliffs;

    @Column(name = "UNDERAGE_CHILDREN")
    protected String underageChildren;

    @Composition
    @OneToMany(mappedBy = "questionnaire")
    protected Set<BankTicketLine> bankTickets;

    @Composition
    @OneToMany(mappedBy = "questionnaire")
    protected Set<CurrentLoan> currentLoans;

    @Composition
    @OneToMany(mappedBy = "questionnaire")
    protected Set<SourceOfIncome> incomes;

    @Column(name = "CREDIT_AMOUNT")
    protected String creditAmount;

    @Column(name = "CREDIT_TERM")
    protected String creditTerm;

    @Column(name = "GUARANTORS")
    protected String guarantors;

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
    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }



    public Set<BankTicketLine> getBankTickets() {
        return bankTickets;
    }

    public void setBankTickets(Set<BankTicketLine> bankTickets) {
        this.bankTickets = bankTickets;
    }


    public Set<CurrentLoan> getCurrentLoans() {
        return currentLoans;
    }

    public void setCurrentLoans(Set<CurrentLoan> currentLoans) {
        this.currentLoans = currentLoans;
    }


    public Set<SourceOfIncome> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<SourceOfIncome> incomes) {
        this.incomes = incomes;
    }



    public void setGuarantors(String guarantors) {
        this.guarantors = guarantors;
    }

    public String getGuarantors() {
        return guarantors;
    }




    public void setCreditTerm(String creditTerm) {
        this.creditTerm = creditTerm;
    }

    public String getCreditTerm() {
        return creditTerm;
    }





    public void setProsecutions(String prosecutions) {
        this.prosecutions = prosecutions;
    }

    public String getProsecutions() {
        return prosecutions;
    }

    public void setLitigations(String litigations) {
        this.litigations = litigations;
    }

    public String getLitigations() {
        return litigations;
    }

    public void setDebtOfBeiliffs(String debtOfBeiliffs) {
        this.debtOfBeiliffs = debtOfBeiliffs;
    }

    public String getDebtOfBeiliffs() {
        return debtOfBeiliffs;
    }

    public void setUnderageChildren(String underageChildren) {
        this.underageChildren = underageChildren;
    }

    public String getUnderageChildren() {
        return underageChildren;
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

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setSecondDocument(String secondDocument) {
        this.secondDocument = secondDocument;
    }

    public String getSecondDocument() {
        return secondDocument;
    }

    public void setPropertyInOwn(String propertyInOwn) {
        this.propertyInOwn = propertyInOwn;
    }

    public String getPropertyInOwn() {
        return propertyInOwn;
    }

    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    public String getArrears() {
        return arrears;
    }

    public void setParticipationInLegalEntities(String participationInLegalEntities) {
        this.participationInLegalEntities = participationInLegalEntities;
    }

    public String getParticipationInLegalEntities() {
        return participationInLegalEntities;
    }

    public void setRegistrationAsIp(String registrationAsIp) {
        this.registrationAsIp = registrationAsIp;
    }

    public String getRegistrationAsIp() {
        return registrationAsIp;
    }

    public boolean isNew(){return __new;}
}