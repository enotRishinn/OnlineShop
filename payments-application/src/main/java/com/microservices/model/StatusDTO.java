package com.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class StatusDTO {
    @NotNull
    public int order_id;

    @NotNull
    public OrderStatus status;

    public StatusDTO(@NotNull int order_id, @NotNull OrderStatus status) {
        this.order_id = order_id;
        this.status = status;
    }
}
