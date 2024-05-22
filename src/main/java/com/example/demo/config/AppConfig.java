package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {

    @Bean(name="defaultTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor defaultTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 적절한 값으로 조정
        executor.setMaxPoolSize(20); // 적절한 값으로 조정
        executor.setQueueCapacity(50); // 필요에 따라 설정
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
