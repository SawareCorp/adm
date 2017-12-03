package com.company.adm.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Face implements EnumClass<Integer> {

    individual(1),
    entity(2);

    private Integer id;

    Face(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Face fromId(Integer id) {
        for (Face at : Face.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}