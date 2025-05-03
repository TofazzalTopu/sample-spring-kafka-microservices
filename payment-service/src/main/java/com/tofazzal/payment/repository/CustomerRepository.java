package com.tofazzal.payment.repository;

import org.springframework.data.repository.CrudRepository;
import com.tofazzal.payment.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
