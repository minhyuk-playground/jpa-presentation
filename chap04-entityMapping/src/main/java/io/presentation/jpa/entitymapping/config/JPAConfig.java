package io.presentation.jpa.entitymapping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created By Minhyuk Yoon on 2018. 7. 31.
 */
@Configuration
@Import(value = {
        PropertySourcesPlaceholderConfig.class,
        DataSourceConfig.class
})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "${base.package}.${base.service.package}.repository")
public class JPAConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Environment environment;


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        String basePackage = environment.getProperty("base.package");
        String servicePackage = environment.getProperty("base.service.package");
        String entityPackage = environment.getProperty("base.entity.package");

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan(basePackage + "." + servicePackage + "." + entityPackage);
        localContainerEntityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        localContainerEntityManagerFactoryBean.afterPropertiesSet();

        return localContainerEntityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));
        properties.setProperty("hibernate.id.new_generator_mappings", environment.getProperty("hibernate.id.new_generator_mappings"));
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.ejb.naming_strategy", environment.getProperty("hibernate.ejb.naming_strategy"));
        properties.setProperty("hibernate.discriminator.ignore_explicit_for_joined", environment.getProperty("hibernate.discriminator.ignore_explicit_for_joined"));

        return properties;
    }

    @Bean
    public static PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }
}
