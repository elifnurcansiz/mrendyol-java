package com.emlakjet;

import com.emlakjet.coupon.Coupon;
import com.emlakjet.coupon.CouponType;
import com.emlakjet.payment.CreditCardPayment;
import com.emlakjet.payment.PaymentType;
import com.emlakjet.product.ProductInStock;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MrendyolTest {

    private final HashMap<Integer, Category> categories = new HashMap<>(Map.of(
            1, new Category(1, "Teknoloji"),
            2, new Category(2, "Yiyecek"),
            3, new Category(3, "Petshop"),
            4, new Category(4, "Giyim")
    ));

    private final HashMap<Integer, Account> accounts = new HashMap<>();
    private final HashMap<Integer, ProductInStock> stock = new HashMap<>(Map.of(
            1, new ProductInStock(1, "Phone", "Phone to surf", categories.get(1), 1000, 100),
            2, new ProductInStock(2, "Bread", "Yemelik düz ekmek ", categories.get(2), 10, 100)

    ));
    private final HashMap<Integer, Coupon> coupons = new HashMap<>(Map.of(
            1, new Coupon(1, new Date(System.currentTimeMillis() + 86_400_000), new Date(), CouponType.ABSOLUTE, 200, 100, 100),
            2, new Coupon(2, new Date(System.currentTimeMillis() + 86_400_000), new Date(), CouponType.RELATIVE, 200, 100, 10)
    ));
    private final Mrendyol mrendyol = new Mrendyol(accounts, stock, categories, coupons);

    @Test
    void checkoutWithFiveBreads() {
        Account account = new Account(1, new CreditCardPayment());
        mrendyol.addAccount(account);
        mrendyol.addProductToAccountBasket(1, 2, 5);

        Invoice invoice = mrendyol.checkOutAccountBasket(1, 1, PaymentType.CREDIT_CARD);


        assertEquals(50, invoice.getSubtotal());
        assertEquals(0, invoice.getDiscount());
        assertEquals(2, invoice.getCommission());
        assertEquals(PaymentType.CREDIT_CARD, invoice.getPaymentType());
        assertEquals(20, invoice.getDeliveryCost());
        assertEquals(72, invoice.getTotalPrice());
    }

    @Test
    void checkoutWithFiveBreadsAndOnePhone_withoutDiscount() {
        Account account = new Account(1, new CreditCardPayment());
        mrendyol.addAccount(account);
        mrendyol.addProductToAccountBasket(1, 1, 1);
        mrendyol.addProductToAccountBasket(1, 2, 5);

        Invoice invoice = mrendyol.checkOutAccountBasket(1, -1, PaymentType.CREDIT_CARD);


        assertEquals(1050, invoice.getSubtotal());
        assertEquals(0, invoice.getDiscount());
        assertEquals(32, invoice.getCommission());
        assertEquals(PaymentType.CREDIT_CARD, invoice.getPaymentType());
        assertEquals(0, invoice.getDeliveryCost());
        assertEquals(1082, invoice.getTotalPrice());
    }

    @Test
    void checkoutWithTwoBreadsAndOnePhone_discounted() {
        Account account = new Account(1, new CreditCardPayment());
        mrendyol.addAccount(account);
        mrendyol.addProductToAccountBasket(1, 1, 1);
        mrendyol.addProductToAccountBasket(1, 2, 5);

        Invoice invoice = mrendyol.checkOutAccountBasket(1, 1, PaymentType.CREDIT_CARD);


        assertEquals(1050, invoice.getSubtotal());
        assertEquals(100, invoice.getDiscount());
        assertEquals(29, invoice.getCommission());
        assertEquals(PaymentType.CREDIT_CARD, invoice.getPaymentType());
        assertEquals(0, invoice.getDeliveryCost());
        assertEquals(979, invoice.getTotalPrice());
    }


}