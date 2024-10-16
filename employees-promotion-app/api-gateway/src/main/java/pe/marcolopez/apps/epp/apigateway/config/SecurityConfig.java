package pe.marcolopez.apps.epp.apigateway.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                // permit all
                .pathMatchers("/actuator/**").permitAll()
                // ms-eployee
                .pathMatchers("/api/employee/**").permitAll()
                // others
                .anyExchange().permitAll()
                .and()
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }
}
