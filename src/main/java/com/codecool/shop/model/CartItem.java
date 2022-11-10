package com.codecool.shop.model;


public class CartItem {
    private Product product;


    public CartItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                '}';
    }
}
