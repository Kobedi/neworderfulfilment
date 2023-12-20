package com.zadscience.modern.order.fulfilment.neworderfulfilment;


import com.zadscience.modern.order.fulfilment.neworderfulfilment.controller.NewOrderFulfilmentController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zadscience.modern.order.fulfilment.neworderfulfilment.entities.Customer;
import com.zadscience.modern.order.fulfilment.neworderfulfilment.repository.NewOrderFulfilmentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NewOrderFulfilmentApplicationTest {

    @Autowired
    private NewOrderFulfilmentController controller;

    @Autowired
    private NewOrderFulfilmentRepository newOrderFulfilmentRepository;

    @Autowired
    private MockMvc mockMvc;

    private  String orderTransferRequest = """
                {
                   "transferringCustomer":{
                      "customerId":2000445,
                      "name":"James",
                      "surname":"Sanders",
                      "address":"44 Brio Avenue, Cape Town, 30009",
                      "externalOrder":{
                         "orderNumber":1022334,
                         "orderDate":"03-10-2022",
                         "trackingNumber":"CompetitorRef::12345-2000445",
                         "externalProduct":null,
                         "id":null,
                         "externalOrderReference":"CompetitorRef::1234567-2000445"
                      }
                   },
                   "electableCustomerReference":"12345",
                   "transferringCustomerReference":"CompetitorRef::2000445"
                }
                """;

    @Test
    void testController() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void testNewOrderFulfilmentRepository() throws Exception {
        assertThat(newOrderFulfilmentRepository).isNotNull();
    }

    @Test
    public void testOrderTransfer()
    {
        try {

            ResultActions postResponse = mockMvc.perform(post("/api/v1/fulfilment/transfer/order")
                            .contentType("application/json")
                            .content(orderTransferRequest))
                    .andExpect(status().isCreated());
            postResponse.andExpect(MockMvcResultMatchers.jsonPath("$.transferred").value("true"));

            //Now let's invoke some get method
            ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fulfilment/" +
                    "/show/electable/customers"));
            //After transferring an order from James (E-comms Env 1) to Thandi ( E-comms Env 2) Thandi must now have 2 orders.
            response.andExpect(MockMvcResultMatchers.jsonPath("$[0].orders", hasSize(2)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void givenCustomers_whenGetElectableCustomer_thenListOfCustomers() throws Exception {
        // Given customer Thandi - who is created by application context loading
        List<Customer> customers =newOrderFulfilmentRepository.showElectableCustomers();


        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fulfilment/" +
                "/show/electable/customers"));

        // then - verify the output
        response.andExpect(status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(customers.size())));
        response.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1))); //check if the array size is 1
        response.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Thandi")); //check if the array size is 1
        response.andExpect(MockMvcResultMatchers.jsonPath("$[0].orders", hasSize(1))); //check if the array size is 1
        response.andExpect(MockMvcResultMatchers.jsonPath("$[0].externalOrder").value(nullValue()));


    }











}
