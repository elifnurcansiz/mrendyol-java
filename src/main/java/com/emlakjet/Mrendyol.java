package com.emlakjet;

import com.emlakjet.coupon.Coupon;
import com.emlakjet.payment.Payment;
import com.emlakjet.payment.PaymentType;
import com.emlakjet.product.ProductInStock;

import java.util.HashMap;

public class Mrendyol {

    private final HashMap<Integer, Account> accounts;
    private final HashMap<Integer, ProductInStock> stock;
    private final HashMap<Integer, Category> categories;
    private final HashMap<Integer, Coupon> coupons;

    public Mrendyol() {
        this.accounts = new HashMap<>();
        this.stock = new HashMap<>();
        this.categories = new HashMap<>();
        this.coupons = new HashMap<>();
    }

    public Mrendyol(HashMap<Integer, Account> accounts, HashMap<Integer, ProductInStock> stock, HashMap<Integer, Category> categories, HashMap<Integer, Coupon> coupons) {
        this.accounts = accounts;
        this.stock = stock;
        this.categories = categories;
        this.coupons = coupons;
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public void addProduct(ProductInStock product) {
        stock.put(product.getProductId(), product);
    }

    public void addCategory(Category category) {
        categories.put(category.getCategoryId(), category);
    }

    public void addCoupon(Coupon coupon) {
        coupons.put(coupon.getCouponId(), coupon);
    }

    public void addProductToAccountBasket(Integer accountId, Integer productId, Integer amount) {
        accounts.get(accountId).addProductToBasket(stock.get(productId), amount);
    }

    public Invoice checkOutAccountBasket(Integer accountId, Integer couponId, PaymentType paymentType) {
        Invoice invoice = accounts.get(accountId).checkout(coupons.get(couponId), paymentType);
        for (int i = 0; i < invoice.getProducts().size(); i++) {
            System.out.printf("%s x%d %dTL%n", invoice.getProducts().get(i).getProductName(), invoice.getProducts().get(i).getAmount(), invoice.getProducts().get(i).getAmount() * invoice.getProducts().get(i).getUnitPrice());
        }
        System.out.println("-".repeat(20));
        System.out.printf("Subtotal: %dTL%n", invoice.getSubtotal());
        System.out.printf("Discount: %dTL%n", invoice.getDiscount());
        System.out.printf("Commission: %dTL%n", invoice.getCommission());
        System.out.println("-".repeat(20));
        System.out.printf("Payment Type: %s%n", invoice.getPaymentType().getName());
        System.out.printf("Delivery Cost: %dTL%n", invoice.getDeliveryCost());
        System.out.printf("Total: %dTL%n%n", invoice.getTotalPrice());
        return invoice;
    }
}
