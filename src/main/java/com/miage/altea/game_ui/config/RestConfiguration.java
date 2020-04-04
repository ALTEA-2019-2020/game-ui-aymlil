package com.miage.altea.game_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    private String username;

    @Value("${trainer.service.password}")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    public RestTemplate trainerApiRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor(username,password);
        restTemplate.setInterceptors(Arrays.asList(interceptor));
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        Interceptor interceptor = new Interceptor();
        restTemplate.setInterceptors(Arrays.asList(interceptor));
        return restTemplate;
    }

}