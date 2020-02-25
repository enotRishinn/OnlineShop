package com.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Item {
    @NotNull
    private int id;

    @NotNull
    private String name;

    @NotNull
    private float price;

    private int amount;

    public Item(@NotNull int id, @NotNull String name, @NotNull float price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
