package com.zadscience.modern.order.fulfilment.neworderfulfilment.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DeliveryConfirmationResponse implements Serializable {

    private DeliveryConfirmation deliveryConfirmation;

}
