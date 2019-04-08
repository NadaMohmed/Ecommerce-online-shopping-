package com.example.ecommerce.project;

public class ProductPrice {
    float price;
    int quantity;

    public ProductPrice(float price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
