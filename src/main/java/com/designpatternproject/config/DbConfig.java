package com.designpatternproject.config;

import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration("DbConfiguration")
@EntityScan(basePackages = "com.designpatternproject.entity")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.designpatternproject.repository"},
        entityManagerFactoryRef = "jpaEntityManagerFactory",
        transactionManagerRef= "jpaTransactionManager"
)
public class DbConfig {

    private final DataSource dataSource;

    private final JpaVendorAdapter vendorAdapter;

    public DbConfig(DataSource dataSource, JpaVendorAdapter vendorAdapter) {
        this.dataSource = dataSource;
        this.vendorAdapter = vendorAdapter;
    }

    @PersistenceContext(unitName = "jpa")
    @Primary
    @Bean(name = "jpaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(this.dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(this.vendorAdapter);
        entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactoryBean.setPackagesToScan("com.designpatternproject.entity");
        entityManagerFactoryBean.setPersistenceUnitName("jpa");
        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean;
    }

    @Primary
    @Bean(name = "securityTransactionManager")
    public PlatformTransactionManager jpaTransactionManager(
            final @Qualifier("jpaEntityManagerFactory") LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(jpaEntityManagerFactory.getObject()));
    }

    @Primary
    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager jpaTransactionManagerSave(
            final @Qualifier("jpaEntityManagerFactory") LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(jpaEntityManagerFactory.getObject()));
    }
}