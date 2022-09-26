package com.emlakjet.payment;

public enum PaymentType {
    CREDIT_CARD("Kredi Kartı"),
    CASH("Nakit");

    private String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
