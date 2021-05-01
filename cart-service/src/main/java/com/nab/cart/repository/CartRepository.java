package com.nab.cart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nab.cart.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>{
	
	Page<Cart> findByUserEmail(String userEmail , Pageable pageable);
}
