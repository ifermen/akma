package com.ifermen.akma.infraestructure.config;

import com.ifermen.akma.domain.service.ApiKeyFormatService;
import com.ifermen.akma.domain.service.BCryptHashingService;
import com.ifermen.akma.infraestructure.adapter.out.security.BCryptHashingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfig {

    @Bean
    public ApiKeyFormatService apiKeyFormatService() {
        return new ApiKeyFormatService(new BCryptHashingServiceImpl());
    }
}
