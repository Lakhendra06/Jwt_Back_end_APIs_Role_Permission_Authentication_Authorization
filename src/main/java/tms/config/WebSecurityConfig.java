package tms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/").hasAnyRole("EMPLOYEE","USER","HOD","ADMIN","SUPERADMIN")
                .requestMatchers("/string").hasAnyRole("EMPLOYEE","USER","HOD","ADMIN","SUPERADMIN")
                .requestMatchers("/employees/**").hasAnyRole("EMPLOYEE","USER","HOD","ADMIN","SUPERADMIN")
                .requestMatchers("/departments/**").hasAnyRole("EMPLOYEE","USER","HOD","ADMIN","SUPERADMIN")
                .requestMatchers("/departments/**").hasAnyRole("EMPLOYEE","USER","HOD","ADMIN","SUPERADMIN")
                .requestMatchers("/roles/**").hasAnyRole("EMPLOYEE","USER","HOD","ADMIN","SUPERADMIN")
                .requestMatchers("/getUserName").authenticated()
                .requestMatchers("/under-maintenance",
                "/js/**",
                "/login",
                "/assets/**",
                "/template_js/**",
                "/dist/**",
                "/getCurrentDate").permitAll().anyRequest().authenticated());
                
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
