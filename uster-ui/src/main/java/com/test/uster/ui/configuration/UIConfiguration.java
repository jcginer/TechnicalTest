package com.test.uster.ui.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 * The type Ui configuration.
 */
@Configuration
public class UIConfiguration {

    /**
     * Configure jackson.
     *
     * @param objectMapper the object mapper
     */
    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }
}
