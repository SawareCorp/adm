package com.company.adm.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Direction implements EnumClass<String> {

    Juridical("1"),
    Bookkeeping("2"),
    Lending("3");

    private String id;

    Direction(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Direction fromId(String id) {
        for (Direction at : Direction.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}