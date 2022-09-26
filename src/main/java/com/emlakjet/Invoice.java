package com.emlakjet;

import com.emlakjet.payment.PaymentType;
import com.emlakjet.product.ProductInBasket;

import java.util.List;

public class Invoice {
    private final List<ProductInBasket> products;
    private final int subtotal;
    private final int discount;
    private final int commission;
    private final PaymentType paymentType;
    private final int deliveryCost;
    private final int totalPrice;

    public Invoice(List<ProductInBasket> products, int subtotal, int discount, int commission, PaymentType paymentType, int deliveryCost, int totalPrice) {
        this.products = products;
        this.subtotal = subtotal;
        this.discount = discount;
        this.commission = commission;
        this.paymentType = paymentType;
        this.deliveryCost = deliveryCost;
        this.totalPrice = totalPrice;
    }

    public List<ProductInBasket> getProducts() {
        return products;
    }



    public int getSubtotal() {
        return subtotal;
    }

    public int getDiscount() {
        return discount;
    }

    public int getCommission() {
        return commission;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
