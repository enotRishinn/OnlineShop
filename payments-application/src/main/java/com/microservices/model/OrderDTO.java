package com.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class OrderDTO {
    @NotNull
    private int order_id;

    public OrderDTO(@NotNull int order_id) {
        this.order_id = order_id;
    }
}
