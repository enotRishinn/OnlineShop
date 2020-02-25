package com.microservices.apigateway.controller;

import com.microservices.apigateway.model.Order;
import com.microservices.apigateway.model.OrderPaid;
import com.microservices.apigateway.model.Payment;
import com.microservices.apigateway.service.ApiGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping (value = "orderpaid")
public class ApiGatewayController {
    private ApiGatewayService apiGatewayService;

    @Autowired
    public ApiGatewayController(ApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }

    @GetMapping (value = "{orderId}")
    OrderPaid getPaymentsForOrder(@RequestParam int orderId) {
        Order order = apiGatewayService.getOrder(orderId);
        ArrayList<Payment> payments = apiGatewayService.getPayments(orderId);
        return new OrderPaid(order, payments);
    }
}
