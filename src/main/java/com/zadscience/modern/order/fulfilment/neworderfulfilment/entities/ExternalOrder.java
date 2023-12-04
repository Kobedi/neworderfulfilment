package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ExternalOrder extends Order implements Serializable {

    private Long id;
    private String externalOrderReference;
}
