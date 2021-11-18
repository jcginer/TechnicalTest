package com.test.uster.ws.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

/**
 * The type Ws configuration.
 */
@Configuration
@ComponentScan(basePackages = {"com.test.uster.business.vehicle.*",
        "com.test.uster.business.driver.*",
        "com.test.uster.business.trip.*"})
@EntityScan(basePackages = {"com.test.uster.persistence.vehicle.*",
        "com.test.uster.persistence.driver.*",
        "com.test.uster.persistence.trip.*"})
@EnableJpaRepositories(basePackages = {"com.test.uster.persistence.vehicle.*",
        "com.test.uster.persistence.driver.*",
        "com.test.uster.persistence.trip.*"})
public class WSConfiguration {

    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }
}
