package com.app.FamilyForeCast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FamilyForeCastSecurityConfig {

    @Bean
    SecurityFilterChain familyForeCastBasicAuthenticationAndAuthorization(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                request -> request.requestMatchers("/cashcards/**")
                        .authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
        User.UserBuilder testUsers = User.builder();
        UserDetails admin = testUsers
                .username("admin")
                .password(passwordEncoder.encode("test@1234"))
                .roles()
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

}
