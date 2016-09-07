package com.fing.domain;

/**
 * Created by jmsmuy on 20/08/16.
 */
public enum EnumItemCategory {
    DATA("Datos", 1),
    MOBILE_TELEPHONE("Telefonia Movil", 2),
    RED_STRAWBERRY("Red Strawberry", 3);

    private final String friendlyName;
    private final Integer enumValue;

    EnumItemCategory(String friendlyName, Integer enumValue) {
        this.friendlyName = friendlyName;
        this.enumValue = enumValue;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public Integer getEnumValue() {
        return enumValue;
    }
}
