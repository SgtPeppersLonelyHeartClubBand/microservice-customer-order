package com.customer.repo;

import com.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    
    @Query(value = "SELECT * FROM customers WHERE email = ?1", nativeQuery = true)
    List<Customer> findByEmail(String email);
}
