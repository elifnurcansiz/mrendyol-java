package com.emlakjet;

import com.emlakjet.coupon.Coupon;
import com.emlakjet.coupon.CouponType;
import com.emlakjet.payment.PaymentType;
import com.emlakjet.product.ProductInStock;
import org.junit.jupiter.api.Test;
import com.emlakjet.payment.CreditCardPayment;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    public void checkoutTest() {

        CreditCardPayment paymentMethod = new CreditCardPayment();
        ProductInStock product = new ProductInStock(1, "technologicaltool", "kırmızı telefon", new Category(1, "Teknoloji"), 350, 2);

        Basket basket = new Basket();
        basket.addProduct(product, 2);

        Invoice actual = basket.checkOut(null, paymentMethod, PaymentType.CREDIT_CARD);

        assertNotNull(actual);

    }

    @Test
    void calculateSubtotalTest() {
        ProductInStock product1 = new ProductInStock(1, "technologicaltool", "kırmızı telefon", new Category(1, "Teknoloji"), 1000, 2);
        ProductInStock product2 = new ProductInStock(2, "ekmek", "ekmek", new Category(2, "Yiyecek"), 10, 5);

        Basket basket = new Basket();
        basket.addProduct(product1, 1);
        basket.addProduct(product2, 5);

        int subtotal = basket.calculateSubtotal();

        assertEquals(1050, subtotal);
    }

    @Test
    void calculateDiscountTest() {
        Coupon coupon = new Coupon(1, new Date(System.currentTimeMillis() + 36_000_000), new Date(), CouponType.ABSOLUTE, 200, 100, 100);
        Basket basket = new Basket();

        int discount = basket.calculateDiscount(coupon, 1000);

        assertEquals(100, discount);
    }

    @Test
    void calculateTotalCommissionTest() {
        Basket basket = new Basket();

        int commission = basket.calculateTotalCommission(PaymentType.CREDIT_CARD, 100);

        assertEquals(3, commission);
    }

    @Test
    void calculateDeliveryCostTest() {
        Basket basket = new Basket();

        int deliveryCost = basket.calculateDeliveryCost(199);

        assertEquals(20, deliveryCost);
    }
}