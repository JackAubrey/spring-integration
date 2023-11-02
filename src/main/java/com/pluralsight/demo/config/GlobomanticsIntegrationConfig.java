package com.pluralsight.demo.config;

import com.pluralsight.demo.service.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
public class GlobomanticsIntegrationConfig {
    public static final String REGISTRATION_REQUEST_CHANNEL = "registrationRequestChannel";
    /*
    Old example used in the previous java configuration code.
    I leave it there just as example
     */
//    @Bean("registrationRequestChannel")
//    public MessageChannel registrationRequestChannel() {
//        return MessageChannels.direct().getObject();
//    }

    /**
     * Note: unlike the old java config, we specify the ID of message channel.
     * No more bean's name is needed
     * @return
     */
    @Bean
    public MessageChannel registrationRequestChannel() {
        return MessageChannels.direct(REGISTRATION_REQUEST_CHANNEL).getObject();
    }

    /**
     * Now that we have our message-channel we can configure your IntegrationFlow.
     * Since we are configuring this flow for a specific channel we can omit the MessageChannel and use directly the channel ID
     *
     * @param registrationService
     */
    @Bean
    public IntegrationFlow registrationRequestFlow(RegistrationService registrationService) {
        return IntegrationFlow
                // in this case we use a string. the string represents the message-channel ID.
                // we could use also the message-channel reference.
                .from(REGISTRATION_REQUEST_CHANNEL)
                // now lets to handle message received activating the "handle method"
                // this has the same effect of the ServiceActivator annotation.
                .handle(registrationService, "register")
                // "get" means: ok the integration flow ends here.
                // that is the same to says ".build()"
                .get();
    }
}
