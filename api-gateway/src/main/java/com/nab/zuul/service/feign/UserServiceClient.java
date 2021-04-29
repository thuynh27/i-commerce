package com.nab.zuul.service.feign;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nab.zuul.dto.UserDTO;

import feign.FeignException;

@FeignClient(name = "users-ws", fallbackFactory = UserServiceFallbackFactory.class)
public interface UserServiceClient {
	
	@PostMapping("/authentication")
	public String getUserDetails(@RequestBody UserDTO userDTO) ;
}


@Component
class UserServiceFallbackFactory implements FallbackFactory<UserServiceClient> {

	@Override
	public UserServiceClient create(Throwable cause) {
		return new UserServiceServiceClientFallback(cause);
	}

}

class UserServiceServiceClientFallback implements UserServiceClient {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Throwable cause;

	public UserServiceServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public String getUserDetails(UserDTO userDTO) {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}

		return StringUtils.EMPTY;
	}

}