package com.fing.grupo09.domain;

/**
 * Created by jmsmuy on 20/08/16.
 */
public enum EnumPaymentMethod {
    CASH("Efectivo", "E"),
    CHECK("Cheque", "C"),
    DEBIT("Debito", "D"),
    CREDIT("Credito", "X");

    private final String friendlyName;
    private final String enumValue;

    EnumPaymentMethod(String friendlyName, String enumValue) {
        this.friendlyName = friendlyName;
        this.enumValue = enumValue;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getEnumValue() {
        return enumValue;
    }
}
