package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Customer implements Serializable {

    private Long customerId;
    private String name;
    private String surname;
    private String address;
    //Assuming we will initially allow only one order to be transferred.
    private ExternalOrder externalOrder;
    //All transferred orders should seamlessly be part of the receiving customer order history

    private List<Order> orders = new ArrayList<Order>();

}
