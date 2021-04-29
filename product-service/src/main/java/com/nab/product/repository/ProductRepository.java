package com.nab.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.product.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	    
    public Page<Product> findAllByProductName(String name ,Pageable pageable);
}
