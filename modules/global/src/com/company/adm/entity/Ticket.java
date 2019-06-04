package com.company.adm.entity;

import javax.annotation.PostConstruct;
import javax.persistence.*;

import com.company.adm.service.AdmConfig;
import com.company.adm.service.TicketService;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import com.haulmont.cuba.core.entity.Updatable;

import java.util.ArrayList;
import java.util.Date;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.Composition;
import java.util.List;

import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;
import com.haulmont.chile.core.annotations.NamePattern;
import com.company.adm.entity.scheduler.CustomScheduler;
import com.company.adm.entity.contracts.Comment;
import com.haulmont.cuba.core.entity.annotation.Listeners;

@Listeners("adm_TicketEntityListener")
@NamePattern("%s|name")
@Table(name = "ADM_TICKET")
@Entity(name = "adm$Ticket")
public class Ticket extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -6023527586307099342L;

    @Lob
    @Column(name = "NAME", nullable = false)
    protected String name;

    @Pattern(message = "{msg://com.company.adm.entity/phoneError}", regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{6,10}")
    @Column(name = "PHONE", nullable = false, length = 20)
    protected String phone;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @Composition
    @OneToMany(mappedBy = "ticket")
    protected List<Contact> contacts;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INTEREST_ID")
    protected Interest interest;

    @Column(name = "SOURCE", nullable = false)
    protected Integer source;

    @NotNull
    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SOURCE_DESCRIPTION_ID")
    protected SourceDescription sourceDescription;

    @Column(name = "AMOUNT")
    protected String amount;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STATUS_ID")
    protected TicketState status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STATUS_DATE_TIME")
    protected Date statusDateTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_CALL")
    protected Date lastCall;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_MEETING")
    protected Date lastMeeting;

    @Lookup(type = LookupType.DROPDOWN)
    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RESPONSIBLE_MANAGER_ID")
    protected User responsibleManager;

    @Column(name = "CHARACTERISTIC", length = 1000)
    protected String characteristic;

    @NotNull
    @Column(name = "FACE", nullable = false)
    protected Integer face;

    @Pattern(message = "{msg://com.company.adm.entity/emailValidationError}", regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
    @Column(name = "E_MAIL")
    protected String eMail;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @Composition
    @OrderBy("dateTime")
    @OneToMany(mappedBy = "ticket")
    protected List<Comment> history;

    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIRE_ID")
    protected Questionnaire questionnaire;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "ticket")
    protected List<CustomScheduler> customScheduler;

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

    public void setCustomScheduler(List<CustomScheduler> customScheduler) {
        this.customScheduler = customScheduler;
    }

    public List<CustomScheduler> getCustomScheduler() {
        return customScheduler;
    }


    public TicketState getStatus() {
        return status;
    }

    public void setStatus(TicketState status) {
        this.status = status;
    }




    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }



    public void setSourceDescription(SourceDescription sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public SourceDescription getSourceDescription() {
        return sourceDescription;
    }


    public void setHistory(List<Comment> history) {
        this.history = history;
    }

    public List<Comment> getHistory() {
        return history;
    }




    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getEMail() {
        return eMail;
    }


    public Face getFace() {
        return face == null ? null : Face.fromId(face);
    }

    public void setFace(Face face) {
        this.face = face == null ? null : face.getId();
    }



    public Date getStatusDateTime() {
        return statusDateTime;
    }

    public void setStatusDateTime(Date statusDateTime) {
        this.statusDateTime = statusDateTime;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }


    public void setLastCall(Date lastCall) {
        this.lastCall = lastCall;
    }

    public Date getLastCall() {
        return lastCall;
    }

    public void setLastMeeting(Date lastMeeting) {
        this.lastMeeting = lastMeeting;
    }

    public Date getLastMeeting() {
        return lastMeeting;
    }

    public void setResponsibleManager(User responsibleManager) {
        this.responsibleManager = responsibleManager;
    }

    public User getResponsibleManager() {
        return responsibleManager;
    }


    public void setSource(Source source) {
        this.source = source == null ? null : source.getId();
    }

    public Source getSource() {
        return source == null ? null : Source.fromId(source);
    }


    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public Interest getInterest() {
        return interest;
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

    public boolean isNew(){
        return __new;
    }

    @PostConstruct
    public void init(){
        customScheduler = new ArrayList<>();
        TicketService ticketService = AppBeans.get(TicketService.class);
        status = ticketService.getDefaultTicketStatus();
        setSource(Source.Incoming);
        face = Face.individual.getId();

        User defaultUser = ticketService.getUserByLogin(
                AppBeans.get(Configuration.class)
                        .getConfig(AdmConfig.class)
                        .getDefaultTicketResponsibleUserLogin()
        );
        if(defaultUser != null)
            setResponsibleManager(defaultUser);
    }

}