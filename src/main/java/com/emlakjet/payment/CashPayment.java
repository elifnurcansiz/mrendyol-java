package com.emlakjet.payment;

public class CashPayment implements Payment {

    @Override
    public boolean pay(int totalPrice) {
        return true;
    }
}
