package com.nab.product.service;

import org.springframework.stereotype.Service;

import com.nab.product.model.ErrorMessage;

@Service
public interface ErrorMessageService {
	
	public ErrorMessage saveErrorMessage(ErrorMessage errorMessage);
}
