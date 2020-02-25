package com.microservices.apigateway.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class OrderPaid {
    private Order order;
    private ArrayList<Payment> payments;

    public OrderPaid(Order order, ArrayList<Payment> payments) {
        this.order = order;
        this.payments = payments;
    }
}
