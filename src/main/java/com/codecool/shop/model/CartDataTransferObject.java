package com.codecool.shop.model;

import java.math.BigDecimal;

public class CartDataTransferObject {

    private Product product;
    private int quantity;
    private BigDecimal sumPricePerProduct;

    public CartDataTransferObject(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.sumPricePerProduct = getProduct().getDefaultPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSumPricePerProduct() {
        return sumPricePerProduct;
    }
}