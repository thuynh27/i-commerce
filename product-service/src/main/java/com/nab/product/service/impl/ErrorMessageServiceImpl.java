package com.nab.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.product.model.ErrorMessage;
import com.nab.product.repository.ErrorMessageRepository;
import com.nab.product.service.ErrorMessageService;

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {
	
	private ErrorMessageRepository errorMessageRepository;

	@Autowired
	public ErrorMessageServiceImpl(ErrorMessageRepository errorMessageRepository) {
		this.errorMessageRepository = errorMessageRepository;
	}

	@Override
	public ErrorMessage saveErrorMessage(ErrorMessage errorMessage) {
		return errorMessageRepository.save(errorMessage);
	}

}
