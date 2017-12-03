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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s %s|name,incomeAmount")
@Table(name = "ADM_SOURCE_OF_INCOME")
@Entity(name = "adm$SourceOfIncome")
public class SourceOfIncome extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -4588083887481564335L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "EXPERIENCE")
    protected String experience;

    @Column(name = "POSITION_")
    protected String position;

    @Column(name = "DOCUMENT")
    protected String document;

    @Column(name = "URGENCY")
    protected String urgency;

    @Column(name = "INCOME_AMOUNT")
    protected Integer incomeAmount;

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

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
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

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExperience() {
        return experience;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocument() {
        return document;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setIncomeAmount(Integer incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Integer getIncomeAmount() {
        return incomeAmount;
    }


}