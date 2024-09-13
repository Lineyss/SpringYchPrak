package com.praktic.Shish.Config;

import java.util.Collections;
import com.praktic.Shish.Model.User;
import jakarta.annotation.PostConstruct;
import com.praktic.Shish.Model.Help.Enum.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import com.praktic.Shish.Interface.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultUser() {
        User user = userRepository.findAll()
                                .stream()
                                .filter(element -> element.getLogin().equals("admin"))
                                .findFirst()
                                .orElse(null);

        String password = passwordEncoder.encode("123321");

        if (user == null){
            user = new User("admin",password, true, Collections.singleton(ERole.ADMIN));
            userRepository.save(user);
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
                    User user = userRepository.findAll()
                            .stream()
                            .filter(element -> element.getLogin().equals(username))
                            .findFirst()
                            .orElse(null);

                    if (user == null) {
                        throw new UsernameNotFoundException("Такой пользователь не существует!");
                    }
                    return new org.springframework.security.core.userdetails.User(
                            user.getLogin(),
                            user.getPassword(),
                            user.isActive(),
                            true,
                            true,
                            true,
                            user.getRoles()
                    );
                }
        ).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                    authorize.requestMatchers("/login", "/registration")
                            .permitAll()
                            .anyRequest()
                            .authenticated())
                    .formLogin(form -> form.loginPage("/login").
                            defaultSuccessUrl("/")
                            .permitAll())
                    .logout(logout -> logout.permitAll())
                    .csrf(csrf -> csrf.disable())
                    .cors(cors -> cors.disable());

        return http.build();
    }

}

