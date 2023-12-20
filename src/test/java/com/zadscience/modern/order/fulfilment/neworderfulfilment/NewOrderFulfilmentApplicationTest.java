package com.zadscience.modern.order.fulfilment.neworderfulfilment;


import com.zadscience.modern.order.fulfilment.neworderfulfilment.controller.NewOrderFulfilmentController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

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

    @Test
    void testController() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void testNewOrderFulfilmentRepository() throws Exception {
        assertThat(newOrderFulfilmentRepository).isNotNull();
    }

    @Test
    public void givenCustomers_whenGetElectableCustomer_thenListOfCustomers() throws Exception {
        // Given customer Thandi - who is created by application context loading
        List<Customer> customers =newOrderFulfilmentRepository.showElectableCustomers();


        // when - action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fulfilment/" +
                "/show/electable/customers"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(customers.size())));
        response.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1))); //check if the array size is 1
        response.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Thandi")); //check if the array size is 1

    }







}
