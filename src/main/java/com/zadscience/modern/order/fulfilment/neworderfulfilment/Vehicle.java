package com.zadscience.modern.order.fulfilment.neworderfulfilment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {

    private double value;

    public abstract double calculateValue(Vehicle v);
}
