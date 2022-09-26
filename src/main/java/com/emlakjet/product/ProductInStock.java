package com.emlakjet.product;

import com.emlakjet.Category;

public class ProductInStock extends Product {

    private int unitInStock;

    public ProductInStock(int productId, String productName, String explanation, Category category, int unitPrice, int unitInStock) {
        super(productId, productName, explanation, category, unitPrice);
        this.unitInStock = unitInStock;
    }

    public boolean isUnitInStock() {
        return unitInStock > 0;
    }

    public boolean isUnitInStock(Integer amount) {
        return unitInStock >= amount;
    }

    public void increaseStock(int amount) {
        unitInStock = unitInStock + amount;
    }

    public void decreaseStock(int amount) {
        unitInStock = unitInStock - amount;
    }
}
