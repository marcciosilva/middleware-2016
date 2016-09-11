package com.fing.domain;

/**
 * Created by jmsmuy on 20/08/16.
 */
public enum EnumCurrency {
    PESOS("Pesos", 858),
    DOLARES("Dolares", 840),
	ERROR("Error", 200);

    private final String friendlyName;
    private final Integer enumValue;

    EnumCurrency(String friendlyName, Integer enumValue) {
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
