package com.company.adm.entity.contracts;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import com.company.adm.entity.Direction;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.core.entity.Versioned;
import javax.persistence.Version;
import com.haulmont.cuba.core.entity.Updatable;
import java.util.Date;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.haulmont.chile.core.annotations.NamePattern;
import com.company.adm.entity.contracts.analytics.Analytics;
import com.haulmont.chile.core.annotations.Composition;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

@NamePattern("%s|name")
@Table(name = "ADM_SERVICE")
@Entity(name = "adm$Service")
public class Service extends BaseUuidEntity implements Versioned, Updatable, Creatable {
    private static final long serialVersionUID = -1137957965144395742L;

    @Column(name = "NAME", nullable = false, length = 1000)
    protected String name;

    @Column(name = "DIRECTION", nullable = false)
    protected String direction;

    @Pattern(message = "{msg://com.company.adm.entity.contracts/costValidationError}", regexp = "^([0-9]*|[0-9]{1,2}%)$")
    @Column(name = "DEFAULT_TAX", nullable = false)
    protected String defaultTax;

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

    public String getDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(String defaultTax) {
        this.defaultTax = defaultTax;
    }


    public Direction getDirection() {
        return direction == null ? null : Direction.fromId(direction);
    }

    public void setDirection(Direction direction) {
        this.direction = direction == null ? null : direction.getId();
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


}