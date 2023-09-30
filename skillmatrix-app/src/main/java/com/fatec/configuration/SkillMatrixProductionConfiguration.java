package com.fatec.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("PROD")
public class SkillMatrixProductionConfiguration {

    private static final String INSTANCE_CONNECTION_NAME =
            System.getenv("INSTANCE_CONNECTION_NAME");
    private static final String INSTANCE_UNIX_SOCKET = System.getenv("INSTANCE_UNIX_SOCKET");
    private static final String DB_USER = System.getenv("DB_USERNAME");
    private static final String DB_PASS = System.getenv("DB_PASSWORD");
    private static final String DB_NAME = System.getenv("DB_NAME");

    @Bean
    @Primary
    public HikariDataSource createConnectionPool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(String.format("jdbc:postgresql:///%s", DB_NAME));
        config.setUsername(DB_USER);
        config.setPassword(DB_PASS);

        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", INSTANCE_CONNECTION_NAME);

        if (INSTANCE_UNIX_SOCKET != null) {
            config.addDataSourceProperty("unixSocketPath", INSTANCE_UNIX_SOCKET);
        }

        config.addDataSourceProperty("ipTypes", "PUBLIC,PRIVATE");
        configureConnectionPool(config);
        return new HikariDataSource(config);
    }
    private static HikariConfig configureConnectionPool(HikariConfig config) {
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(10000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        return config;
    }
}