package multitenancy;

import be.civadis.gpdoc.config.ApplicationProperties;
import be.civadis.gpdoc.security.oauth2.AudienceValidator;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Beans spring nécessaire au multitenant
 */
@Configuration
public class MultitenantConfig {

    private final Logger log = LoggerFactory.getLogger(MultitenantConfig.class);

    private ApplicationProperties applicationProperties;
    private final JHipsterProperties jHipsterProperties;

    public MultitenantConfig(ApplicationProperties applicationProperties, JHipsterProperties jHipsterProperties) {
        this.applicationProperties = applicationProperties;
        this.jHipsterProperties = jHipsterProperties;
    }

    @Primary
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JwtDecoder jwtDecoder(){
        String issuerUri = applicationProperties.getMultitenancy().getIssuerBaseUri() + TenantContext.getCurrentTenant();

        NimbusJwtDecoderJwkSupport jwtDecoder = (NimbusJwtDecoderJwkSupport) JwtDecoders.fromOidcIssuerLocation(issuerUri);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator();
        //OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(jHipsterProperties.getSecurity().getOauth2().getAudience());
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;

    }
}
