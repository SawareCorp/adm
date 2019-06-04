package com.company.adm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.company.adm.entity.contracts.Contract;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.Creatable;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@NamePattern("%s|contract")
@Table(name = "ADM_CASH_WARRANT")
@Entity(name = "adm$CashWarrant")
public class CashWarrant extends BaseLongIdEntity implements Creatable {
    private static final long serialVersionUID = -5540585316144423469L;

    @Column(name = "CREATE_TS")
    protected Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy;

    @Min(message = "Слишком маленькая сумма", value = 1)
    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    protected Long amount;

    @NotNull
    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONTRACT_ID")
    protected Contract contract;

    @NotNull
    @Column(name = "AMOUNT_WORDS", nullable = false)
    protected String amountWords;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


    public void setAmountWords(String amountWords) {
        this.amountWords = amountWords;
    }

    public String getAmountWords() {
        return amountWords;
    }


    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }


}