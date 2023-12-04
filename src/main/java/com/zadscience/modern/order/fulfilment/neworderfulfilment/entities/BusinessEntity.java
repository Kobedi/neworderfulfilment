package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BusinessEntity implements Serializable {

    private Customer transferringCustomer;
    private String electableCustomerReference;
    private String transferringCustomerReference;
}
