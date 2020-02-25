package com.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AddPayment {
    public PaymentStatus status;
    @NotNull
    public int orderId;

    public AddPayment(PaymentStatus status, @NotNull int orderId) {
        this.status = status;
        this.orderId = orderId;
    }
}
