package com.company.adm.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Source implements EnumClass<Integer> {

    Incoming(1),
    Repeated(2),
    REFUSAL(3);

    private Integer id;

    Source(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Source fromId(Integer id) {
        for (Source at : Source.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}