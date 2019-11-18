package multitenancy;

import be.civadis.gpdoc.config.ApplicationProperties;
import be.civadis.gpdoc.config.LiquibaseContextTestCondition;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.internal.AbstractSessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class MultitenantDatabaseConfig {

    private final Logger log = LoggerFactory.getLogger(MultitenantDatabaseConfig.class);

    private final Environment env;
    private JpaProperties jpaProperties;
    private ApplicationProperties applicationProperties;
    private AbstractSessionImpl tmp;

    public MultitenantDatabaseConfig(Environment env, JpaProperties jpaProperties, ApplicationProperties applicationProperties) {
        this.env = env;
        this.jpaProperties = jpaProperties;
        this.applicationProperties = applicationProperties;
    }

    //hibernate

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Conditional(LiquibaseContextTestCondition.class)
    @Bean(name = "currentTenantIdentifierResolver")
    public ContextBasedTenantIdentifierResolver contextBasedTenantIdentifierResolver(){
        return new ContextBasedTenantIdentifierResolver();
    }

    @Bean(name = "multiTenantConnectionProvider")
    public DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider() {
        HashMap<String, DataSource> dataSources = new HashMap<String, DataSource>();

        applicationProperties.getMultitenancy().getTenants().stream().forEach(tc -> {
            DataSource ds = DataSourceBuilder
                .create()
                .username(tc.getUsername())
                .password(tc.getPassword())
                .url(tc.getUrl())
                .build();

            if (ds instanceof HikariDataSource){
                ((HikariDataSource) ds).setAutoCommit(tc.getHikari().isAutoCommit());
                ((HikariDataSource) ds).setDataSourceProperties(tc.getHikari().getDataSourceProperties());
            }

            dataSources.put(tc.getName(), ds);
        });

        log.warn("datasources : " + dataSources.size());
        log.warn(applicationProperties.getMultitenancy().getDefaultTenant().toString());

        return new DataSourceBasedMultiTenantConnectionProviderImpl(applicationProperties.getMultitenancy().getDefaultTenant().getName(), dataSources);
    }

    @Primary
    @Bean
    @DependsOn("multiTenantConnectionProvider")
    public DataSource dataSource() {
        return dataSourceBasedMultiTenantConnectionProvider().getDefaultDataSource();
    }

    @Conditional(LiquibaseContextTestCondition.class)
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       HibernateProperties hibernateProperties, JpaProperties jpaProperties,
                                                                       MultiTenantConnectionProvider multiTenantConnectionProvider,
                                                                       CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings().ddlAuto(() -> {return "none";}));

        properties.put(org.hibernate.cfg.Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("be.civadis", "org.axonframework.modelling.saga.repository.jpa", "org.axonframework.eventsourcing.eventstore.jpa");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaPropertyMap(properties);

        return em;

    }

}
