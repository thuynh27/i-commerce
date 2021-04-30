package com.nab.product.aop;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.nab.product.dto.ProductTracking;
import com.nab.product.model.ErrorMessage;
import com.nab.product.producer.ProductSender;
import com.nab.product.service.ErrorMessageService;
import com.nab.product.utils.SecurityUtils;

@Aspect
@Component
public class TrackingProductAOP {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrackingProductAOP.class);

	private ErrorMessageService errorMessageService;

	private ProductSender productSender;

	@Autowired
	public TrackingProductAOP(ErrorMessageService errorMessageService, ProductSender productSender) {
		this.errorMessageService = errorMessageService;
		this.productSender = productSender;
	}

	@Before("execution(* com.nab.product.controller.ProductController.getProduct(..))")
	@Async
	public void getProductAspect(JoinPoint joinPoint) {
		try {
			LOGGER.info("================ Tracking Viewing Product Service ========================");
			Object[] args = joinPoint.getArgs();
			Long productId = (Long) args[0];
			ProductTracking productTracking = populateProductTracking(productId);
			productSender.sendMessageToQueue(productTracking);
			LOGGER.info("================ Tracking Viewing Product Service Successfully ========================");
		} catch (Exception e) {
			errorTracking(joinPoint, e);
		}
	}

	@Before("execution(* com.nab.product.controller.ProductController.search(..))")
	@Async
	public void searchProductAspect(JoinPoint joinPoint) {
		try {
			LOGGER.info("================ Tracking Search Product Service ========================");
			Object[] args = joinPoint.getArgs();
			Long productId = (Long) args[0];
			ProductTracking productTracking = populateProductTracking(productId);
			populateProductView(productTracking, joinPoint);
			productSender.sendMessageToQueue(productTracking);
			LOGGER.info("================ Tracking Search Product Service Scuccessfully ========================");
		} catch (Exception e) {
			errorTracking(joinPoint, e);
		}
	}

	private ProductTracking populateProductTracking(Long productId) {
		ProductTracking productTracking = new ProductTracking();
		productTracking.setProductID(productId);
		Optional<String> userName = SecurityUtils.getCurrentUserLogin();
		if (userName.isPresent()) {
			productTracking.setUserEmail(userName.get());
		}
		return productTracking;
	}

	private void populateProductView(ProductTracking productTracking, JoinPoint joinPoint) {
		//TODO Tracking Search , Filter , Sort 
	}

	private void errorTracking(JoinPoint joinPoint, Exception e) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(e.getMessage());
		errorMessage.setFunctionTracking(joinPoint.getSignature().getName());
		errorMessage.setDescription(e.getLocalizedMessage());
		errorMessage.setParams(Arrays.toString(joinPoint.getArgs()));
		errorMessageService.saveErrorMessage(errorMessage);
	}
}
