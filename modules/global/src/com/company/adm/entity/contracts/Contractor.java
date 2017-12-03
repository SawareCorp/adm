package com.company.adm.entity.contracts;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.company.adm.entity.Contact;
import com.company.adm.entity.Face;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import javax.persistence.Version;
import com.haulmont.cuba.core.entity.Updatable;
import com.haulmont.cuba.core.entity.Creatable;
import javax.persistence.UniqueConstraint;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Lob;
import javax.validation.constraints.Pattern;
import com.haulmont.chile.core.annotations.Composition;
import java.util.List;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.FileDescriptor;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@NamePattern("%s|name")
@Table(name = "ADM_CONTRACTOR", uniqueConstraints = {
    @UniqueConstraint(name = "IDX_ADM_CONTRACTOR_IND_UNQ", columnNames = {"NAME", "DATE_BORN"}),
    @UniqueConstraint(name = "IDX_ADM_CONTRACTOR_ENT_UNQ", columnNames = {"NAME", "TIN"})
})
@Entity(name = "adm$Contractor")
public class Contractor extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -2065638257871138305L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "REPRESENTATIVE")
    protected String representative;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_BORN")
    protected Date dateBorn;

    @Pattern(message = "{msg://com.company.adm.entity/phoneError}", regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{6,10}")
    @Column(name = "PHONE", length = 50)
    protected String phone;

    @Column(name = "FACE", nullable = false)
    protected Integer face;

    @Composition
    @OneToMany(mappedBy = "contractor")
    protected List<Contact> contacts;

    @Column(name = "COMMENT_", length = 1000)
    protected String comment;

    @Column(name = "PASSPORT", length = 20)
    protected String passport;

    @Column(name = "ISSUING_AUTHORITY")
    protected String issuingAuthority;

    @Temporal(TemporalType.DATE)
    @Column(name = "ISSUE_DATE")
    protected Date issueDate;

    @Column(name = "DIVISION_CODE", length = 10)
    protected String divisionCode;

    @Column(name = "SNAILS")
    protected String snails;

    @Column(name = "ADDRESS")
    protected String address;

    @Column(name = "TIN", length = 20)
    protected String tin;

    @Column(name = "KPP", length = 20)
    protected String kpp;

    @Column(name = "OGRN")
    protected String ogrn;

    @Column(name = "JURIDICAL_ADDRESS")
    protected String juridicalAddress;

    @Column(name = "POST_ADDRESS")
    protected String postAddress;

    @JoinTable(name = "ADM_CONTRACTOR_FILE_DESCRIPTOR_LINK",
        joinColumns = @JoinColumn(name = "CONTRACTOR_ID"),
        inverseJoinColumns = @JoinColumn(name = "FILE_DESCRIPTOR_ID"))
    @ManyToMany
    protected List<FileDescriptor> files;

    @Column(name = "EMAIL")
    protected String email;

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


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setFiles(List<FileDescriptor> files) {
        this.files = files;
    }

    public List<FileDescriptor> getFiles() {
        return files;
    }


    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setSnails(String snails) {
        this.snails = snails;
    }

    public String getSnails() {
        return snails;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getTin() {
        return tin;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getKpp() {
        return kpp;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setJuridicalAddress(String juridicalAddress) {
        this.juridicalAddress = juridicalAddress;
    }

    public String getJuridicalAddress() {
        return juridicalAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostAddress() {
        return postAddress;
    }


    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


    public void setFace(Face face) {
        this.face = face == null ? null : face.getId();
    }

    public Face getFace() {
        return face == null ? null : Face.fromId(face);
    }


    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
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

    public boolean isNew(){return __new;}
}