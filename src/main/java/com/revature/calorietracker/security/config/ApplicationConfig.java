package com.revature.calorietracker.security.config;

import com.revature.calorietracker.dto.UserSecurityDTO;
import com.revature.calorietracker.repos.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService() {

//        return (username) -> {
//            System.out.println("ApplicationConfig.userDetailsService: " + username);
//            UserSecurityDTO userSecurityDTO = userRepo.findUserSecurityDTOByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//            //System.out.println("ApplicationConfig.userDetailsService: " + userSecurityDTO);
//            return userSecurityDTO;
//        };
        return this::getUserSecurityDTO;
    }
    private UserSecurityDTO getUserSecurityDTO(String username){
        return userRepo.findUserSecurityDTOByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//    @Bean
//    public AuditorAware<Integer> auditorAware(){
//        return new ApplicationAuditAware();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
