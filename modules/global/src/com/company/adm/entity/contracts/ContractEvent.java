package com.company.adm.entity.contracts;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ContractEvent implements EnumClass<Integer> {

    Call(10),
    Meeting(20);

    private Integer id;

    ContractEvent(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ContractEvent fromId(Integer id) {
        for (ContractEvent at : ContractEvent.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}