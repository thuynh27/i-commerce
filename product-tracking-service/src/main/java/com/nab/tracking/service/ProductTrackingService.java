package com.nab.tracking.service;

import org.springframework.stereotype.Service;

import com.nab.tracking.model.ProductTracking;

@Service
public interface ProductTrackingService {
	
	public ProductTracking saveProductTracking(ProductTracking productTracking);

}
