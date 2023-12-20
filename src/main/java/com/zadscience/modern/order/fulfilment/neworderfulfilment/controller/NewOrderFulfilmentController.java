package com.zadscience.modern.order.fulfilment.neworderfulfilment.controller;


import com.zadscience.modern.order.fulfilment.neworderfulfilment.entities.*;
import com.zadscience.modern.order.fulfilment.neworderfulfilment.repository.NewOrderFulfilmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v1/fulfilment")
public class NewOrderFulfilmentController {

    @Autowired
    NewOrderFulfilmentRepository newOrderFulfilmentRepository;

    @PostMapping(value = "/confirmdelivery")
    public ResponseEntity confirmdelivery(@RequestBody ConfirmDeliveryRequest request) {

        DeliveryConfirmationResponse deliveryConfirmationResponse=null;
        boolean found = newOrderFulfilmentRepository.confirmDelivery(request);
        if(found)
        {
            deliveryConfirmationResponse = newOrderFulfilmentRepository.
                    getDeliveryConfirmation(request.getTrackingNumber());
            return new ResponseEntity(deliveryConfirmationResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/transfer/order")
    public ResponseEntity transferOrder(@RequestBody BusinessEntity businessEntity) {

        BusinessEntityResponse response = new BusinessEntityResponse();
        response = newOrderFulfilmentRepository.transferOrder(businessEntity);
        if(response.isTransferred())
        {
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
    }

    @PostMapping(value = "/transfer/order/subscribe")
    public ResponseEntity<Customer> subscribe(@RequestBody Customer customer) {

        if(newOrderFulfilmentRepository.subscribe(customer))
        {
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/show/electable/customers")
    public List showElectableCustomers() {
        List customers = newOrderFulfilmentRepository.showElectableCustomers();
        return customers;
    }

}
