package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Product implements Serializable {

    private Long productId;
    private String name;
    private String description;

    private String type;









}
