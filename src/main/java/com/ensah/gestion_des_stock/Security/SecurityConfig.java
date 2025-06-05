package com.ensah.gestion_des_stock.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author $ {USER}
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/h2-console/**","/login","/login.css","/stock.jpg").permitAll()
                       .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")                         // page GET login
                        .loginProcessingUrl("/login")                // POST target pour le formulaire
                        .defaultSuccessUrl("/entrepots", true)      // redirection après succès
                        .failureUrl("/login?error=true")             // redirection après échec
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")               // URL pour déclencher le logout (POST par défaut)
                        .logoutSuccessUrl("/login?logout") // URL après déconnexion réussie
                        .invalidateHttpSession(true)       // Invalide la session
                        .deleteCookies("JSESSIONID")       // Supprime le cookie de session
                        .permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")
                        .disable())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        //http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


    @Autowired
    DataSource dataSource;
    @Bean
    public UserDetailsService userDetailsService(){


        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        if (!userDetailsManager.userExists(admin.getUsername())) {
            userDetailsManager.createUser(admin);
        }
        return userDetailsManager;
        //return new InMemoryUserDetailsManager(user1,admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
