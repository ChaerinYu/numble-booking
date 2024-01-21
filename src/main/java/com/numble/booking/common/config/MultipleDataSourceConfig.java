package com.numble.booking.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * <pre>
 * Class Name : MultipleDataSourceConfig
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
@Configuration
@EnableConfigurationProperties
public class MultipleDataSourceConfig {
    @Primary
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.database.booking-datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
