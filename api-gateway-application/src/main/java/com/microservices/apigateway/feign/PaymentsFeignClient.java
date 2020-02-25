package com.microservices.apigateway.feign;

import com.microservices.apigateway.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@FeignClient(name="payments-service")
public interface PaymentsFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "payments/order/{orderId}")
    ArrayList<Payment> getPayments(@PathVariable int orderId);
}
