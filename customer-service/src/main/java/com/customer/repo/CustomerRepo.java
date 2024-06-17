package com.customer.repo;

import com.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
    Optional<Customer> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM customers WHERE name LIKE %:criteria% OR email LIKE %:criteria%", nativeQuery = true)
    List<Customer> findByCriteria(@Param("criteria") String criteria);

    @Query(value = "SELECT * FROM customers WHERE total_spent > :amount", nativeQuery = true)
    List<Customer> findCustomersWithSpendingOver(@Param("amount") Double amount);
}
