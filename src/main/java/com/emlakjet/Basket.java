package com.emlakjet;

import com.emlakjet.coupon.Coupon;
import com.emlakjet.payment.Payment;
import com.emlakjet.payment.PaymentType;
import com.emlakjet.product.ProductInBasket;
import com.emlakjet.product.ProductInStock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Basket {
    private static final int DELIVERY_COST_LOWER_LIMIT=200;
    private  static final int DELIVERY_COST=20;

    public List<ProductInBasket> products = new ArrayList<>();

    public Invoice checkOut(Coupon coupon, Payment paymentMethod, PaymentType paymentType) {

        int subtotal = calculateSubtotal();
        int discount = calculateDiscount(coupon, subtotal);
        int commission = calculateTotalCommission(paymentType, subtotal - discount);
        int deliveryCost = calculateDeliveryCost(subtotal - discount);
        int totalPrice = subtotal - discount + commission + deliveryCost;

        boolean checkOutStatus = paymentMethod.pay(totalPrice); // direkt paymentMethod objesine erişip pay demek yerine account üzerinden başka bir metod ile payment yapılabilir.

        if (checkOutStatus) {
            Invoice invoice = new Invoice(this.products, subtotal, discount, commission, paymentType, deliveryCost, totalPrice);
            this.products = new ArrayList<>();
            return invoice;
        }

        // bir yere daha kaydedilebilir. satın alma geçmişi gibi bir yer oluşturulup invoice oluşturulup metoda invoice return değeri verilip alınan değer satın alma geçmişine eklenebilir.

        return null;
    }

    public int calculateSubtotal() {
        int subtotal = 0;
        for (int i = 0; i < products.size(); i++) {
            subtotal = subtotal + products.get(i).getUnitPrice() * products.get(i).getAmount(); // ürün fiyatlarını topla / toplam sepet fiyatını hesapla
        }
        return subtotal;
    }

    public int calculateDiscount(Coupon coupon, int subtotal) {
        if (coupon != null && coupon.isValid(new Date(), subtotal)) {
            return coupon.getTotalDiscount(subtotal); // kupon tutarınca fiyat düş
        }
        return 0;
    }

    public int calculateTotalCommission(PaymentType paymentType, int priceWithoutCommission) {
        if (paymentType.equals(PaymentType.CREDIT_CARD)) {
            return (int) Math.round(((float) priceWithoutCommission) * 0.03);
        }
        return 0;
    }

    public int calculateDeliveryCost(int priceWithoutDeliveryCost) {
        if (priceWithoutDeliveryCost < DELIVERY_COST_LOWER_LIMIT) {
            return DELIVERY_COST;
        }
        return 0;
    }

    public void addProduct(ProductInStock product, Integer amount) {
        if (product.isUnitInStock(amount)) {
            product.decreaseStock(amount);
            products.add(new ProductInBasket(product.getProductId(), product.getProductName(), product.getExplanation(), product.getCategory(), product.getUnitPrice(), amount));

        }
    }
}
