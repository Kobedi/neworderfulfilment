package com.zadscience.modern.order.fulfilment.neworderfulfilment;

public class VehicleCalculations {

    public double calculateValue(Vehicle v)
    {
        if (v instanceof Car)
        {
            return v.getValue() * 0.8;
        }
        if(v instanceof Bike)
        {
            return v.getValue() * 0.5;
        }
        return 0.0;
    }

}
