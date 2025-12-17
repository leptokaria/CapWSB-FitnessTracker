package pl.wsb.fitnesstracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Wyłączamy CSRF, aby łatwiej testować w Postmanie
                .authorizeHttpRequests(auth -> auth
                        // 1. Actuator dostępny bez logowania (zgodnie z wymaganiami)
                        .requestMatchers("/actuator/**").permitAll()

                        // 2. Metody GET (pobieranie) dostępne dla każdego zalogowanego użytkownika
                        .requestMatchers(HttpMethod.GET, "/v1/users/**").authenticated()

                        // 3. Metody zmieniające dane (POST, PUT, DELETE) tylko dla ADMINA
                        .anyRequest().hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults()); // Logowanie typu Basic Auth

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Tworzymy użytkowników w pamięci (zgodnie z wymaganiem "lista statyczna")

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}