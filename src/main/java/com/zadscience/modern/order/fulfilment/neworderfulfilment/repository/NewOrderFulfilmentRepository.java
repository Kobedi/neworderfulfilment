package com.zadscience.modern.order.fulfilment.neworderfulfilment.repository;

import com.zadscience.modern.order.fulfilment.neworderfulfilment.entities.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public class NewOrderFulfilmentRepository {

    private final List<DeliveryConfirmationResponse> deliveryConfirmationResponses;

    private final List<Customer> electableCustomers;
        public NewOrderFulfilmentRepository() {

            DeliveryConfirmation confirmation  = new DeliveryConfirmation();
            DeliveryConfirmationResponse response = new DeliveryConfirmationResponse();
            deliveryConfirmationResponses = new ArrayList<>();
            electableCustomers = new ArrayList<>();
            Customer customer = new Customer();
            customer.setName("Thandi");
            customer.setSurname("Newton");
            customer.setCustomerId(Long.parseLong("12345"));

            Order order = new Order();
            order.setTrackingNumber("12345");
            order.setOrderNumber(Long.parseLong("1234567"));

            Shipping shipping = new Shipping();
            //This date is equal order purchase date + 7 days, excluding weekends
            shipping.setShippingDate("ShippingDate");
            shipping.setTrackingNumber(order.getTrackingNumber());

            Product product = new Product();
            product.setProductId(Long.parseLong("10795"));
            product.setName("Mobile");
            product.setType("iPhone");
            product.setDescription("Fragile");
            List<Product> products = new ArrayList<>();
            products.add(product);
            order.setProducts(products);
            customer.getOrders().add(order);
            //Now add this customer to electable customers. Making it visible to a competitor
            //platform.
            electableCustomers.add(customer);
            confirmation.setOrder(order);
            confirmation.setShipping(shipping);
            confirmation.setTrackingNumber(order.getTrackingNumber());
            //This date is equal order purchase date + 7 days, excluding weekends
            confirmation.setEstimatedDeliveryDate(shipping.getShippingDate());
            response.setDeliveryConfirmation(confirmation);
            deliveryConfirmationResponses.add(response);
        }

        public List<Customer> showElectableCustomers()
        {
            return electableCustomers;
        }

    public List<Customer> subscribe(Customer customer)
    {
        boolean result = electableCustomers.add(customer);
        return electableCustomers;
    }

    public Customer showElectableCustomers(Long customerId)
    {
        for(Customer customer: electableCustomers)
        {//For the customer who matches the transferred order customer id
            if(customer.getCustomerId()==customerId)
            {   //return their details
                return customer;
            }
        }
        return null;
    }


    public BusinessEntityResponse transferOrder(BusinessEntity businessEntity)
    {
        //Notice I am not testing for null values. Goodluck!
        Customer transferringCustomer = businessEntity.getTransferringCustomer();
        BusinessEntityResponse response = new BusinessEntityResponse();
        // The receiving peer customer reference
        response.setReceivingCustomerReference(businessEntity.getElectableCustomerReference());
        //Their own unique reference
        response.setTransferringCustomerReference(businessEntity.getTransferringCustomerReference());
        // and finally the reference that belong to the order
        // that has been transferred.
        response.setTransferringOrderReference(transferringCustomer
                .getExternalOrder().getExternalOrderReference());
        for(Customer customer: electableCustomers)
        {//For the customer who matches the transferred order Electable Customer Id
            if(customer.getCustomerId()==Long.parseLong(businessEntity.getElectableCustomerReference()))
            {   //add the transferred new order to their order collection and history
                customer.getOrders().add(transferringCustomer.getExternalOrder());
                response.setTransferred(true);
                break;
            }
        }
        return response;
    }

    public BusinessEntityResponse transferOrder(ExternalPeerCustomer externalPeerCustomer,
                                                    String electableCustomerReference)
        {

            System.out.println(externalPeerCustomer + " KKK "+ electableCustomerReference);
            ExternalPeerCustomer extlPeerCustomer = new ExternalPeerCustomer();
            BusinessEntityResponse response = new BusinessEntityResponse();
            // The receiving peer customer reference
            response.setReceivingCustomerReference(electableCustomerReference);
            //Their own unique reference
            response.setTransferringCustomerReference(externalPeerCustomer.getExternalCustomerReference());
            // and finally the reference that belong to the order
            // that has been transferred.
            response.setTransferringOrderReference(extlPeerCustomer.getExternalOrder()
                    .getExternalOrderReference());
            for(Customer customer: electableCustomers)
            {//For the customer who matches the transferred order customer id
                if(customer.getCustomerId()==Long.parseLong(electableCustomerReference))
                {   //add the new order to their order collection and history
                    customer.getOrders().add(extlPeerCustomer.getExternalOrder());
                    response.setTransferred(true);
                    break;
                }
            }
            return response;
        }

        public List<DeliveryConfirmationResponse> findAll() {
            return deliveryConfirmationResponses;
        }

        public DeliveryConfirmationResponse getDeliveryConfirmation(String trackingNumber)
        {
            for(DeliveryConfirmationResponse response: deliveryConfirmationResponses)
            {
                if(response.getDeliveryConfirmation().getTrackingNumber()
                        .equals(trackingNumber))
                {
                    return response;
                }
            }
            return null;
        }
        public boolean confirmDelivery(ConfirmDeliveryRequest request) {

            for(DeliveryConfirmationResponse responses: deliveryConfirmationResponses)
            {
                if(responses.getDeliveryConfirmation().getTrackingNumber()
                        .equals(request.getTrackingNumber()))
                {
                    return true;
                }
                if(responses.getDeliveryConfirmation().getOrder().getOrderNumber()
                        .equals(Long.parseLong(request.getOrderNumber())))
                {
                    return true;
                }
            }
            return false;
        }
    }

