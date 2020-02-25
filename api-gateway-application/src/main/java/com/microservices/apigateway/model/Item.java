package com.microservices.apigateway.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("price")
    private float price;

    @JsonCreator
    public Item(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("amount") int amount,
                @JsonProperty("price") float price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
}
