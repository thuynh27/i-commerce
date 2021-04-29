package com.nab.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nab.product.model.ProductHistory;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long>{

}
