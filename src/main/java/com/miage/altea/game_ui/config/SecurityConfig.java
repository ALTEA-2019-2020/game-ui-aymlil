package com.miage.altea.game_ui.config;

import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TrainerService trainerS;

    public TrainerService getTrainerS() {
        return trainerS;
    }

    public void setTrainerS(TrainerService trainerS) {
        this.trainerS = trainerS;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> Optional.ofNullable(trainerS.getTrainerByName(username)).map(trainer ->
                User.withUsername(trainer.getName()).password(trainer.getPassword()).roles("USER").build())
                .orElseThrow(() -> new BadCredentialsException("No such user"));
    }
}
