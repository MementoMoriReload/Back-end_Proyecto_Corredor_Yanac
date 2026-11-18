package com.example.gateway.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Seguridad del gateway, controlada por propiedades:
 *
 *  gateway.security.enabled=false  -> todo abierto (para desarrollo y primeras pruebas)
 *  gateway.security.enabled=true   -> exige un JWT (HS256) valido en "Authorization: Bearer ..."
 *                                     salvo en las rutas de gateway.security.public-paths
 *
 * El gateway solo VALIDA tokens. Quien los emite (login) es otro microservicio
 * que debe firmar con el mismo secreto.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http,
            @Value("${gateway.security.enabled:false}") boolean enabled,
            @Value("${gateway.security.public-paths:/actuator/health}") String[] publicPaths) {

        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable);

        if (enabled) {
            http.authorizeExchange(exchanges -> exchanges
                    // El preflight de CORS nunca lleva token
                    .pathMatchers(HttpMethod.OPTIONS).permitAll()
                    .pathMatchers(publicPaths).permitAll()
                    .anyExchange().authenticated())
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
        } else {
            http.authorizeExchange(exchanges -> exchanges.anyExchange().permitAll());
        }
        return http.build();
    }

    @Bean
    @ConditionalOnProperty(name = "gateway.security.enabled", havingValue = "true")
    ReactiveJwtDecoder jwtDecoder(@Value("${gateway.security.jwt-secret:}") String secret) {
        if (secret.getBytes(UTF_8).length < 32) {
            throw new IllegalStateException(
                    "gateway.security.jwt-secret (JWT_SECRET) debe tener al menos 32 caracteres para HS256");
        }
        SecretKey key = new SecretKeySpec(secret.getBytes(UTF_8), "HmacSHA256");
        return NimbusReactiveJwtDecoder.withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}
