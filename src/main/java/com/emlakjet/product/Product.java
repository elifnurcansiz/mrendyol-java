package com.emlakjet.product;

import com.emlakjet.Category;

public abstract class Product {

    protected int productId;
    protected String productName;
    protected String explanation;
    protected Category category;
    protected int unitPrice;

    public Product(int productId, String productName, String explanation, Category category, int unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.explanation = explanation;
        this.category = category;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getExplanation() {
        return explanation;
    }

    public Category getCategory() {
        return category;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
