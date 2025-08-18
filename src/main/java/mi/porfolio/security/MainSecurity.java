package mi.porfolio.security;

import mi.porfolio.security.jwt.JwtEntryPoint;
import mi.porfolio.security.jwt.JwtTokenFilter;
import mi.porfolio.security.service.UserDetailsImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MainSecurity {

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS aquí
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints públicos
                        .requestMatchers("/api/auth/login", "/api/auth/create").permitAll()
                        .requestMatchers("/api/personas/lista").permitAll()
                        .requestMatchers("/api/experiencias/lista").permitAll()
                        .requestMatchers("/api/habilidades/lista").permitAll()
                        .requestMatchers("/api/cursos/lista").permitAll()
                        .requestMatchers("/api/educacion/lista").permitAll()
                        .requestMatchers("/api/domicilios/lista").permitAll()
                        .requestMatchers("/api/edad/lista").permitAll()
                        .requestMatchers("/api/personas/*/foto").permitAll()
                        // Endpoints protegidos (USER o ADMIN)
                        .requestMatchers(
                                "/api/experiencias/**",
                                "/api/habilidades/**",
                                "/api/cursos/**",
                                "/api/personas/**",
                                "/api/educacion/**",
                                "/api/domicilios/**"
                        ).hasAnyRole("ADMIN", "USER")
                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:4200",
                "https://porfolio-c8697.web.app"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
