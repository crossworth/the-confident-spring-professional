package br.dev.pedro.springboot.starter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vibur.dbcp.ViburDBCPDataSource;
import org.vibur.dbcp.ViburDataSource;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(ViburDataSource.class)
@ConditionalOnMissingBean(DataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(PedroDataSourceProperties.class)
public class PedroAutoConfiguration {

    @Bean
    public ViburDBCPDataSource dataSource(PedroDataSourceProperties pedroDataSourceProperties) {
        ViburDBCPDataSource ds = new ViburDBCPDataSource();
        ds.setJdbcUrl(pedroDataSourceProperties.getUrl());
        ds.setUsername(pedroDataSourceProperties.getUsername());
        ds.setPassword(pedroDataSourceProperties.getPassword());
        ds.setDriverClassName(pedroDataSourceProperties.getDriverClassName());
        ds.start();
        return ds;
    }
}
