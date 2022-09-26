package com.emlakjet.payment;

public class CreditCardPayment implements Payment {


    @Override
    public boolean pay(int totalPrice) {

        return true;
    }

}

