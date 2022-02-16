package com.deo.jet.ship.configuration;

import com.deo.jet.common.processor.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagesConfiguration {

    @Bean
    public MessageConverter converter() {
        return new MessageConverter();
    }

}
