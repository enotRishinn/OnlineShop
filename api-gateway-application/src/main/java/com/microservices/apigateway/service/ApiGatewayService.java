package com.microservices.apigateway.service;

import com.microservices.apigateway.model.Order;
import com.microservices.apigateway.model.Payment;

import java.util.ArrayList;

public interface ApiGatewayService {
    Order getOrder(int orderId);
    ArrayList<Payment> getPayments(int orderId);
}
