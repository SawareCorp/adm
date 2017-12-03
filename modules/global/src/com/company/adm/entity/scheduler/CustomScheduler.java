package com.company.adm.entity.scheduler;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import javax.persistence.Column;
import javax.persistence.Version;
import com.haulmont.cuba.core.entity.Updatable;
import java.util.Date;
import com.haulmont.cuba.core.entity.Creatable;
import com.company.adm.entity.Ticket;
import com.company.adm.entity.contracts.Contract;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

@NamePattern("%s %s %s|date,ticket,contract")
@Table(name = "ADM_CUSTOM_SCHEDULER")
@Entity(name = "adm$CustomScheduler")
public class CustomScheduler extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -8811932752931156989L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")
    protected Ticket ticket;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @NotNull
    @Column(name = "WORKED_OUT", nullable = false)
    protected Boolean workedOut = false;

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



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRACT_ID")
    protected Contract contract;

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public void setWorkedOut(Boolean workedOut) {
        this.workedOut = workedOut;
    }

    public Boolean getWorkedOut() {
        return workedOut;
    }


    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
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