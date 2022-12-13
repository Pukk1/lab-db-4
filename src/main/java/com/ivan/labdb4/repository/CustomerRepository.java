package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByUsername(String username);
}
