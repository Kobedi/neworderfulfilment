package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class Order implements Serializable {

    private Long orderNumber;
    private String orderDate;
    private String trackingNumber;
    private ExternalProduct externalProduct;
    @JsonIgnore
    private List<Product> products;

}
