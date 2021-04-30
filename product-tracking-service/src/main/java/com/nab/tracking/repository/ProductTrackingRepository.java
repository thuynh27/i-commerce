package com.nab.tracking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nab.tracking.model.ProductTracking;

@Repository
public interface ProductTrackingRepository extends MongoRepository<ProductTracking, String>{

}
