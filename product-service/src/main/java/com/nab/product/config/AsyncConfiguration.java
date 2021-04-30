package com.nab.product.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
	
	/**
	 * If we want to have access to the principal inside the async thread, just as
	 * we have access to it outside, we'll need to create the
	 * DelegatingSecurityContextAsyncTaskExecutor
	 */
    @Override
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(1);
        executor.setThreadGroupName("MyCustomExecutor");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setBeanName("asyncExecutor");
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }

}
