package com.numble.booking.common.config;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <pre>
 * Class Name : BookingRepositoryConfig
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-26 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-26
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.numble.booking.**.**.repository")
public class BookingRepositoryConfig {

    @Primary
    @Bean(name = "jpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

}
