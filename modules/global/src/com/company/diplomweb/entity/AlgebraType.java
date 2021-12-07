package com.company.diplomweb.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum AlgebraType implements EnumClass<String> {

    SLNC("Sl_n(C)"),
    SPNC("Sp_n(C)"),
    ONC("O_n(C)");

    private String id;

    AlgebraType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static AlgebraType fromId(String id) {
        for (AlgebraType at : AlgebraType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}