package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeliveryConfirmation implements Serializable {


    private String trackingNumber;
    private String estimatedDeliveryDate;
    private Order order;
    private Shipping shipping;

}
