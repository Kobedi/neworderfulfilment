package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ExternalPeerCustomer extends Customer implements Serializable {

    private String externalCustomerReference;

    @Override
    public String toString() {
        return "ExternalPeerCustomer{" +
                "externalCustomerReference='" + externalCustomerReference + '\'' +
                '}';
    }
}
