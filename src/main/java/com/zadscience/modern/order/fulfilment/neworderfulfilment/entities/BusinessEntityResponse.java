package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * After the successful transfer of an order from
 * one competitor platform to speedorder platform,
 * the sending customer want to see the following:

*/
@Getter
@Setter
public class BusinessEntityResponse implements Serializable {

    @JsonIgnore
    private Long uniqueId;
    // The receiving peer customer reference
    private String receivingCustomerReference;
    //Their own unique reference
    private String transferringCustomerReference;
    // and finally the reference that belong to the order
    // that has been transferred.
    private String transferringOrderReference;
    private boolean transferred;



}
