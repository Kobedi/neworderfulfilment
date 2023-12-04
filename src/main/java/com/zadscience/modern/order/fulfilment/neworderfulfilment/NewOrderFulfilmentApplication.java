package com.zadscience.modern.order.fulfilment.neworderfulfilment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zadscience.modern.order.fulfilment.neworderfulfilment.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.function.Predicate;

@SpringBootApplication
public class NewOrderFulfilmentApplication {

	static StringBuilder result = new StringBuilder("");

	public static void main(String[] args) throws IOException {
		SpringApplication.run(NewOrderFulfilmentApplication.class, args);

	}



}
