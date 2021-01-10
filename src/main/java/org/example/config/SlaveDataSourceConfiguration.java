package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

public class SlaveDataSourceConfiguration {
    @Bean("slaveDataSourceProperties")
    @ConfigurationProperties("spring.datasource-slave")
    DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean("slaveDatasource")
    DataSource dataSource(@Autowired @Qualifier("slaveDataSourceProperties")
                          DataSourceProperties props){
        return props.initializeDataSourceBuilder().build();
    }
}
