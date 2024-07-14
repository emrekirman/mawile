package com.emrekirman.mawile.common.translator.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessagesConfiguration {

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultEncoding("iso-8859-1");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
