package com.numble.booking.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <pre>
 * Class Name : RepositoryConfig
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-01	    user	New
 * </pre>
 *
 * @author user
 * @since 2024-01-01
 */
@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = "com.numble.booking.**.repository")
public class RepositoryConfig {

    @Autowired
    @Qualifier("datasource")
    private DataSource datasource;

    @Primary
    @Bean(name = "jpaProperties")
    @ConfigurationProperties(prefix = "spring.booking-jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("jpaProperties") JpaProperties jpaProperties) {

        Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
        log.info("default Property Load : {}", properties);

        return builder
                .dataSource(datasource)
                .packages(
                        "com.numble.booking"		// 일반 domain 객체
                )
                .persistenceUnit("booking")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean builder) {
        return new JpaTransactionManager(Objects.requireNonNull(builder.getObject()));
    }
}
