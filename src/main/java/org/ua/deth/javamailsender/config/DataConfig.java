package org.ua.deth.javamailsender.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Eugene Khudoliiv.
 * (eugenkhidoliiv@gmail.com)
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan("org.ua.deth.javamailsender.entity")
@EnableJpaRepositories("org.ua.deth.javamailsender.repository")
public class DataConfig {
    //    DB properties:
    private static final String PROP_DATABASE_DRIVER = "spring.datasource.driverClassName";
    private static final String PROP_DATABASE_URL = "spring.datasource.url";
    private static final String PROP_DATABASE_USERNAME = "spring.datasource.username";
    private static final String PROP_DATABASE_PASSWORD = "spring.datasource.password";

    //    Hibernate Configuration:
    private static final String PROP_HIBERNATE_DIALECT = "org.hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_CONNECTION_CHARSET = "db.hibernate.connection.CharSet";
    private static final String PROP_HIBERNATE_CONNECTION_CHARACTERENCODING =
            "db.hibernate.connection.characterEncoding";
    private static final String PROP_HIBERNATE_CONNECTION_USEUNICODE = "db.hibernate.connection.useUnicode";

    //    EntityManager Configuration:
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
    private static final String hibernate_hbm2ddl_files_sql_extractor = "hibernate.hbm2ddl.import_files_sql_extractor";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.put("hibernate.show_sql", env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
        properties.put("hibernate.connection.CharSet", env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_CHARSET));
        properties.put("hibernate.connection.characterEncoding",
                env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_CHARACTERENCODING));
        properties.put("hibernate.connection.useUnicode",
                env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_USEUNICODE));
        properties.put("hibernate.hbm2ddl.import_files_sql_extractor", env.getRequiredProperty(hibernate_hbm2ddl_files_sql_extractor));
        return properties;
    }

}
