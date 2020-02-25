package com.microservices.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ItemDTO {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("amount")
    public Integer amount;

    @JsonCreator
    public ItemDTO(@JsonProperty("id") Integer id, @JsonProperty("amount") Integer amount){
        this.id = id;
        this.amount = amount;
    }
}
