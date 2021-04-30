package com.nab.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nab.product.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	    
    public Page<Product> findAllByProductName(String name ,Pageable pageable);
    
    @Query("select p from Product p where "
    		+ "p.productName like %:keyword% or "
    		+ "p.price like %:keyword% or "
    		+ "p.brand like %:keyword% or "
    		+ "p.color like %:keyword% ")
    public Page<Product> findProductByKeyword(@Param("keyword") String keyword , Pageable pageable);
}
