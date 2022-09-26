package com.emlakjet.product;

import com.emlakjet.Category;

public class ProductInBasket extends Product {

    private final int amount;

    public ProductInBasket(int productId, String productName, String explanation, Category category, int unitPrice, int amount) {
        super(productId, productName, explanation, category, unitPrice);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
