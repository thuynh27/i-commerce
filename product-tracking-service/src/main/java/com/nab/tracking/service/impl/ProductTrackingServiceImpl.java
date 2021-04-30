package com.nab.tracking.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.tracking.model.ProductTracking;
import com.nab.tracking.model.ProductView;
import com.nab.tracking.repository.ProductTrackingRepository;
import com.nab.tracking.repository.ProductViewRepository;
import com.nab.tracking.service.ProductTrackingService;

@Service
public class ProductTrackingServiceImpl implements ProductTrackingService {

	private ProductTrackingRepository productTrackingRepository;

	private ProductViewRepository productViewRepository;

	@Autowired
	public ProductTrackingServiceImpl(ProductTrackingRepository productTrackingRepository,
			ProductViewRepository productViewRepository) {
		this.productTrackingRepository = productTrackingRepository;
		this.productViewRepository = productViewRepository;
	}

	@Override
	public ProductTracking saveProductTracking(ProductTracking productTracking) {
		if(Objects.nonNull(productTracking.getProductView())){
			ProductView productView = productViewRepository.save(productTracking.getProductView());
			productTracking.setProductView(productView);
		}
		return productTrackingRepository.save(productTracking);
	}

}
