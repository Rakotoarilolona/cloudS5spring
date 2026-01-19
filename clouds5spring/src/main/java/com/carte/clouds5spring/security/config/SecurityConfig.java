package com.carte.clouds5spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.carte.clouds5spring.security.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig 
{
	private final JwtAuthenticationFilter jwtAuthFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) 
	{
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.anyRequest().permitAll()  // ← TOUT EST AUTORISÉ
			);
			// .authorizeHttpRequests(auth -> auth
			// 	.requestMatchers("/auth/**").permitAll()
			// 	.requestMatchers("/admin/**").hasRole("admin")
			// 	.anyRequest().authenticated()
			// );
				.requestMatchers(
					"/api/data/routeprobleme",
					"/api/data/routeprobleme/dashboard",
					"/api/data/routeprobleme/{id}",
					"/swagger-ui.html",
					"/swagger-ui/index.html",
					"/swagger-ui/**",
					"/api-docs",
					"/api-docs/**",
					"/v3/api-docs",
					"/v3/api-docs/**"
				).permitAll()
				.anyRequest().authenticated())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception 
	{
		return config.getAuthenticationManager();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ← CORRECT pour la production
    }
}