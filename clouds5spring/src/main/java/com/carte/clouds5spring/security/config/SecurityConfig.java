package com.carte.clouds5spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
			.cors(Customizer.withDefaults()) // 🔥 OBLIGATOIR
			.csrf(csrf -> csrf.disable())
			// .authorizeHttpRequests(auth -> auth
			// 	.requestMatchers("/test").authenticated()
			// 	.anyRequest().permitAll() // ← TOUT EST AUTORISÉ
			// );
			// );
			.authorizeHttpRequests(auth -> auth
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
					"/v3/api-docs/**",
					"/auth/register",
					"/auth/login",
					"/signalements",
					"/admin/firebase-signalements",
					"/admin/sync/firebase",
					"/signalements/{id}"
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
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("http://localhost:5173"); // ton front
		config.addAllowedOriginPattern("http://localhost:5174"); // ton front (Docker port)
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	// @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder(); // ← CORRECT pour la production
    // }
}