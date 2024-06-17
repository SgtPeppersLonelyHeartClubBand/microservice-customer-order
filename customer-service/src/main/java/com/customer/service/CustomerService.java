package com.customer.service;

import com.customer.model.Customer;
import com.customer.repo.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepo customerRepo;

    public Customer saveCustomer(Customer customer) {
        logger.info("Saving customer: {}", customer);
        return customerRepo.save(customer);
    }

    public Customer getCustomerById(Long id) {
        logger.info("Fetching customer with id: {}", id);
        Optional<Customer> customer = customerRepo.findById(id);
        return customer.orElse(null);
    }

    public boolean customerExists(Long id) {
        logger.info("Checking if customer exists with id: {}", id);
        return customerRepo.findById(id).isPresent();
    }

    public List<Customer> getCustomersByCriteria(String criteria) {
        logger.info("Fetching customers with criteria: {}", criteria);
        List<Customer> customers = customerRepo.findByCriteria(criteria);
        return customers.stream()
                .filter(customer -> customer.getName().contains(criteria) || customer.getEmail().contains(criteria))
                .collect(Collectors.toList());
    }

    public List<Customer> getCustomersWithHighSpending(Double amount) {
        logger.info("Fetching customers with spending over: {}", amount);
        return customerRepo.findCustomersWithSpendingOver(amount);
    }
}
