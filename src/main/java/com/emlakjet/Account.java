package com.emlakjet;

import com.emlakjet.coupon.Coupon;
import com.emlakjet.payment.CashPayment;
import com.emlakjet.payment.Payment;
import com.emlakjet.payment.PaymentType;
import com.emlakjet.product.ProductInStock;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final int accountId;
    private final Payment payment;
    private final List<Invoice> invoiceHistory;
    private final Basket basket;

    public Account(int accountId, Payment payment) {
        this.accountId = accountId;
        this.payment = payment;
        this.invoiceHistory = new ArrayList<>();
        this.basket = new Basket();
    }

    public int getAccountId() {
        return accountId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void addInvoice(Invoice invoice) {
        invoiceHistory.add(invoice);
    }

    public List<Invoice> getInvoiceHistory() {
        return invoiceHistory;
    }

    public void addProductToBasket(ProductInStock product, Integer amount) {
        basket.addProduct(product, amount);
    }

    public Invoice checkout(Coupon coupon, PaymentType paymentType) {
        if (paymentType.equals(PaymentType.CREDIT_CARD)) {
            return basket.checkOut(coupon, payment, paymentType);
        } else {
            return basket.checkOut(coupon, new CashPayment(), paymentType);
        }
    }
}
