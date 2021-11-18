package com.test.uster.business.configuration;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Configuration test.
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
public class ConfigurationTest {
    /**
     * Empty test.
     */
    @Test
    void emptyTest() {
        Assert.assertTrue(true);
    }
}
