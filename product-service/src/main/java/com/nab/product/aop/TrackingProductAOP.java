package com.nab.product.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.nab.product.model.ErrorMessage;
import com.nab.product.service.ErrorMessageService;

@Aspect
@Component
public class TrackingProductAOP {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrackingProductAOP.class);

	private ErrorMessageService errorMessageService;

	@Autowired
	public TrackingProductAOP(ErrorMessageService errorMessageService) {
		this.errorMessageService = errorMessageService;
	}

	@Before("execution(* com.nab.product.controller.ProductController.getProduct(..))")
	@Async
	public void getProductAspect(JoinPoint joinPoint) {
		try {
			Object[] args = joinPoint.getArgs();
			Long productId = (Long) args[0];
			LOGGER.info("Tracking Viewing Product Service : {}", productId);
		} catch (Exception e) {
			errorTracking(joinPoint, e);
		}
	}
	
	@Before("execution(* com.nab.product.controller.ProductController.getAll(..))")
	@Async
	public void getAllProductAspect(JoinPoint joinPoint) {
		try {
			LOGGER.info("Tracking Get All Product Service ");
		} catch (Exception e) {
			errorTracking(joinPoint, e);
		}
	}
	
	
	@Before("execution(* com.nab.product.controller.ProductController.search(..))")
	@Async
	public void searchProductAspect(JoinPoint joinPoint) {
		try {
			LOGGER.info("Tracking Search Product Service ");
		} catch (Exception e) {
			errorTracking(joinPoint, e);
		}
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
