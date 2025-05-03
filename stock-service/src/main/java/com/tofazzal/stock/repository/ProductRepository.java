package com.tofazzal.stock.repository;

import org.springframework.data.repository.CrudRepository;
import com.tofazzal.stock.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
