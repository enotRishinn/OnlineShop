package com.microservices.apigateway.feign;

import com.microservices.apigateway.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="orders-service")
public interface OrdersFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/orders/{orderId}")
    Order getOrder(@PathVariable int orderId);
}
