package com.pluralsight.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
public class GlobomanticsIntegrationConfig {
    @Bean("registrationRequestChannel")
    public MessageChannel registrationRequestChannel() {
        return MessageChannels.direct().getObject();
    }
}
