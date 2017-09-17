package com.gergely.jonas.dailyrecipe.web.configuration;

import com.gergely.jonas.dailyrecipe.service.Joke;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.sql.DataSourceDefinition;

@Configuration
public class AppConfig {

    @Bean
    public Joke getJoke() {
        return new Joke();
    }
}
