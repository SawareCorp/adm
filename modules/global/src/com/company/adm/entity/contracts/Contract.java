package com.company.adm.entity.contracts;

import javax.annotation.PostConstruct;
import javax.persistence.*;

import com.company.adm.entity.Direction;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import com.haulmont.cuba.core.entity.Updatable;

import java.util.ArrayList;
import java.util.Date;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.List;

import com.haulmont.chile.core.annotations.Composition;

import javax.validation.constraints.NotNull;

import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;
import com.company.adm.entity.scheduler.CustomScheduler;
import com.company.adm.entity.contracts.analytics.Analytics;
import java.util.Set;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.company.adm.entity.Ticket;
import com.company.adm.entity.Questionnaire;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.company.adm.entity.Source;
import com.company.adm.entity.SourceDescription;

@Listeners("adm_ContractEntityListener")
@NamePattern("№%s %s|contractNumber,contractor")
@Table(name = "ADM_CONTRACT")
@Entity(name = "adm$Contract")
public class Contract extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = 1791056198827139859L;

    @Lookup(type = LookupType.SCREEN, actions = {"lookup", "open"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_ID")
    protected Ticket ticket;

    @NotNull
    @Column(name = "CONTRACT_NUMBER", nullable = false, unique = true)
    protected Long contractNumber;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONTRACTOR_ID")
    protected Contractor contractor;

    @Column(name = "CONTRACT_STATUS", nullable = false)
    protected Integer contractStatus;

    @Column(name = "DIRECTION", nullable = false)
    protected String direction;

    @Column(name = "COMPENSATION")
    protected Integer compensation;

    @Column(name = "AMOUNT")
    protected Long amount;

    @Column(name = "DESIRED_AMOUNT")
    protected String desiredAmount;

    @Composition
    @OneToMany(mappedBy = "contract")
    protected List<ContractServiceLine> services;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE", nullable = false)
    protected Date createDate;

    @Lookup(type = LookupType.DROPDOWN)
    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RESPONSIBLE_MANAGER_ID")
    protected User responsibleManager;

    @Composition
    @OrderBy("dateTime desc")
    @OneToMany(mappedBy = "contract")
    protected List<Comment> history;

    @OrderBy("number")
    @Composition
    @OneToMany(mappedBy = "contract")
    protected List<Analytics> analytics;

    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIRE_ID")
    protected Questionnaire questionnaire;

    @JoinTable(name = "ADM_CONTRACT_FILE_DESCRIPTOR_LINK",
        joinColumns = @JoinColumn(name = "CONTRACT_ID"),
        inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @ManyToMany
    protected List<FileDescriptor> internalFiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_ID")
    protected SourceDescription source;

    @OneToMany(mappedBy = "contract")
    protected List<CustomScheduler> schedules;

    @Column(name = "CONTRACT_EVENT")
    protected Integer contractEvent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EVENT_TIME")
    protected Date eventTime;

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



    public void setContractEvent(ContractEvent contractEvent) {
        this.contractEvent = contractEvent == null ? null : contractEvent.getId();
    }

    public ContractEvent getContractEvent() {
        return contractEvent == null ? null : ContractEvent.fromId(contractEvent);
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Date getEventTime() {
        return eventTime;
    }


    public void setSchedules(List<CustomScheduler> schedules) {
        this.schedules = schedules;
    }

    public List<CustomScheduler> getSchedules() {
        return schedules;
    }


    public String getDesiredAmount() {
        return desiredAmount;
    }

    public void setDesiredAmount(String desiredAmount) {
        this.desiredAmount = desiredAmount;
    }



    public SourceDescription getSource() {
        return source;
    }

    public void setSource(SourceDescription source) {
        this.source = source;
    }







    public void setInternalFiles(List<FileDescriptor> internalFiles) {
        this.internalFiles = internalFiles;
    }

    public List<FileDescriptor> getInternalFiles() {
        return internalFiles;
    }


    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }


    public List<Analytics> getAnalytics() {
        return analytics;
    }

    public void setAnalytics(List<Analytics> analytics) {
        this.analytics = analytics;
    }


    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }




    public void setCompensation(Integer compensation) {
        this.compensation = compensation;
    }

    public Integer getCompensation() {
        return compensation;
    }



    public Direction getDirection() {
        return direction == null ? null : Direction.fromId(direction);
    }

    public void setDirection(Direction direction) {
        this.direction = direction == null ? null : direction.getId();
    }


    public void setHistory(List<Comment> history) {
        this.history = history;
    }

    public List<Comment> getHistory() {
        return history;
    }


    public void setResponsibleManager(User responsibleManager) {
        this.responsibleManager = responsibleManager;
    }

    public User getResponsibleManager() {
        return responsibleManager;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }



    public List<ContractServiceLine> getServices() {
        return services;
    }

    public void setServices(List<ContractServiceLine> services) {
        this.services = services;
    }



    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus == null ? null : contractStatus.getId();
    }

    public ContractStatus getContractStatus() {
        return contractStatus == null ? null : ContractStatus.fromId(contractStatus);
    }


    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
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


    public void setContractNumber(Long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Long getContractNumber() {
        return contractNumber;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public boolean isNew(){
        return __new;
    }

    @PostConstruct
    protected void init(){
        //customScheduler = new ArrayList<>();
        setDirection(Direction.Lending);
    }
}