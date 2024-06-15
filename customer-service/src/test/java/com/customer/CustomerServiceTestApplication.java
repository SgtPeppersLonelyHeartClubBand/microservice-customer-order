package com.customer;

import com.customer.model.Customer;
import com.customer.repo.CustomerRepo;
import com.customer.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTestApplication {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTestApplication.class);

    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomer() {
        logger.info("Running testSaveCustomer");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        when(customerRepo.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        verify(customerRepo, times(1)).save(any(Customer.class));
    }

    @Test
    void testGetCustomerById() {
        logger.info("Running testGetCustomerById");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);

        assertNotNull(foundCustomer);
        assertEquals("John Doe", foundCustomer.getName());
        verify(customerRepo, times(1)).findById(1L);
    }

    @Test
    void testCustomerExists() {
        logger.info("Running testCustomerExists");
        when(customerRepo.existsById(1L)).thenReturn(true);

        boolean exists = customerService.customerExists(1L);

        assertEquals(true, exists);
        verify(customerRepo, times(1)).existsById(1L);
    }
}
