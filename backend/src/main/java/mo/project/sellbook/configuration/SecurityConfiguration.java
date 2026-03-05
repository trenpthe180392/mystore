package mo.project.sellbook.configuration;

import mo.project.sellbook.service.UserDetailServiceCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String[] WHILE_LIST = {
            "/auth/login",
            "/api/users/create",
            "/api/books/home",
            "/api/books/*",
    };

    private final UserDetailServiceCustomizer userDetailsService;
    private final JwtDecoderConfiguration jwtDecoderConfig; // Đổi sang class cụ thể của bạn

    public SecurityConfiguration(UserDetailServiceCustomizer userDetailsService,
                                 JwtDecoderConfiguration jwtDecoderConfig) {
        this.userDetailsService = userDetailsService;
        this.jwtDecoderConfig = jwtDecoderConfig;
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHILE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        // Ép Spring dùng đúng cái class JwtDecoderConfiguration bạn đã viết
                        oauth2.jwt(jwt -> jwt.decoder(jwtDecoderConfig))
                );

        return http.build();
    }
}