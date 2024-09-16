package uz.ilmnajot.register_3_with_jwt_and_thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.enums.Role;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.AuthService;
import uz.ilmnajot.register_3_with_jwt_and_thymeleaf.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;



    public SecurityConfig(JwtFilter jwtFilter, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtFilter = jwtFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("/api/v1/admin/").hasAnyRole(Role.ADMIN.name())
                        .requestMatchers("/api/v1/user/").hasAnyRole(Role.USER.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService.userDetailsService());
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }



}
