package com.nab.user.service.feign;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import feign.FeignException;

@FeignClient(name = "management-ws", fallbackFactory = ManagementFallbackFactory.class)
public interface ManagementServiceClient {
	
	@GetMapping("/managements")
	public String getStatus();
}


@Component
class ManagementFallbackFactory implements FallbackFactory<ManagementServiceClient> {

	@Override
	public ManagementServiceClient create(Throwable cause) {
		return new ManegementServiceClientFallback(cause);
	}

}

class ManegementServiceClientFallback implements ManagementServiceClient {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Throwable cause;

	public ManegementServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public String getStatus() {

		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}

		return StringUtils.EMPTY;
	}

}