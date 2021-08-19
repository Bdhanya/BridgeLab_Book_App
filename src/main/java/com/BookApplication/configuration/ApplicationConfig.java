package com.BookApplication.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
public class ApplicationConfig {
    private static MessageSourceAccessor messageSourceAccessor;

    @PostConstruct
    private void initMessageSourceAccessor() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames( "classpath:messages/messages");
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public static MessageSourceAccessor getMessageAccessor() {
            return messageSourceAccessor;
    }
}
