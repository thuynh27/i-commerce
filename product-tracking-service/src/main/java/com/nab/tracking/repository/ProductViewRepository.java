package com.nab.tracking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nab.tracking.model.ProductView;

@Repository
public interface ProductViewRepository extends MongoRepository<ProductView, String> {

}
