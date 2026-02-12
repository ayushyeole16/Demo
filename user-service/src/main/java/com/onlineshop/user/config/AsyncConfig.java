package com.onlineshop.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    // Default SimpleAsyncTaskExecutor is fine for this use-case.
    // For more control, you can define a ThreadPoolTaskExecutor bean here.
}