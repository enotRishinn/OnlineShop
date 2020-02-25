package com.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Payment {
    @NotNull
    private
    int id;

    private PaymentStatus status;

    @NotNull
    private
    int orderID;

    public Payment(@NotNull int id, PaymentStatus status, @NotNull int orderID) {
        this.id = id;
        this.status = status;
        this.orderID = orderID;
    }
}
