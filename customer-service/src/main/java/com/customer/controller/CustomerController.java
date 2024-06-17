package com.customer.controller;

import com.customer.model.Customer;
import com.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        logger.info("Received request to save customer: {}", customer);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        logger.info("Received request to fetch customer with id: {}", id);
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> customerExists(@PathVariable Long id) {
        logger.info("Received request to check if customer exists with id: {}", id);
        boolean exists = customerService.customerExists(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> getCustomersByCriteria(@RequestParam String criteria) {
        logger.info("Received request to search customers with criteria: {}", criteria);
        List<Customer> customers = customerService.getCustomersByCriteria(criteria);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/high-spending")
    public ResponseEntity<List<Customer>> getCustomersWithHighSpending(@RequestParam Double amount) {
        logger.info("Received request to fetch customers with spending over: {}", amount);
        List<Customer> customers = customerService.getCustomersWithHighSpending(amount);
        return ResponseEntity.ok(customers);
    }
}
