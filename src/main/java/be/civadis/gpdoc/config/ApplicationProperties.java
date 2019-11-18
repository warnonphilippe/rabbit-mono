package be.civadis.gpdoc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Properties specific to Gpdoc.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private Multitenancy multitenancy = new Multitenancy();

    public Multitenancy getMultitenancy() {
        return multitenancy;
    }

    public void setMultitenancy(Multitenancy multitenancy) {
        this.multitenancy = multitenancy;
    }

    public static class Multitenancy {

        private String issuerBaseUri;
        private Tenant defaultTenant;

        private List<Tenant> tenants = new ArrayList<Tenant>();

        @PostConstruct
        public void init() {
            List<Tenant> tcs = tenants.stream().filter(tc -> tc.isDefault()).collect(Collectors.toCollection(ArrayList::new));
            if (tcs.size() > 1) {
                throw new IllegalStateException("Only can be configured as default one data source. Review your configuration");
            }
            this.defaultTenant = tcs.get(0);
        }

        public String getIssuerBaseUri() {
            return issuerBaseUri;
        }

        public void setIssuerBaseUri(String issuerBaseUri) {
            this.issuerBaseUri = issuerBaseUri;
        }

        public List<Tenant> getTenants() {
            return tenants;
        }

        public void setTenants(List<Tenant> tenants) {
            this.tenants = tenants;
        }

        public Tenant getDefaultTenant() {
            if (defaultTenant == null){
                init();
            }
            return defaultTenant;
        }

        public static class Tenant {

            private String name;

            private boolean isDefault;

            private String driverClassName;

            private String url;

            private String username;

            private String password;

            private String type;

            private Hikari hikari = new Hikari();

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDriverClassName() {
                return driverClassName;
            }

            public void setDriverClassName(String driverClassName) {
                this.driverClassName = driverClassName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public boolean isDefault() {
                return isDefault;
            }

            public void setDefault(boolean isDefault) {
                this.isDefault = isDefault;
            }

            public Hikari getHikari() {
                return hikari;
            }

            public void setHikari(Hikari hikari) {
                this.hikari = hikari;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class Hikari{

                private String poolName;
                private boolean isAutoCommit = true;
                private Properties dataSourceProperties = new Properties();

                public String getPoolName() {
                    return poolName;
                }

                public void setPoolName(String poolName) {
                    this.poolName = poolName;
                }

                public boolean isAutoCommit() {
                    return isAutoCommit;
                }

                public void setAutoCommit(boolean autoCommit) {
                    isAutoCommit = autoCommit;
                }

                public Properties getDataSourceProperties() {
                    return dataSourceProperties;
                }

                public void setDataSourceProperties(Properties dataSourceProperties) {
                    this.dataSourceProperties = dataSourceProperties;
                }
            }

        }

    }


}

