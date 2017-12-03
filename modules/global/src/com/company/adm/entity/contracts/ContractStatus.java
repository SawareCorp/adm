package com.company.adm.entity.contracts;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ContractStatus implements EnumClass<Integer> {

    Active(1),
    Inactive(2),
    Closed(3),
    Paid(4),
    NotPaid(5);

    private Integer id;

    ContractStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ContractStatus fromId(Integer id) {
        for (ContractStatus at : ContractStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}