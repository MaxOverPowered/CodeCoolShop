package com.codecool.shop.model;

import java.util.UUID;

public class Order {
    private UUID id;
    private boolean isSuccessful;

    public Order() {
        this.id = UUID.randomUUID();
    }
}
