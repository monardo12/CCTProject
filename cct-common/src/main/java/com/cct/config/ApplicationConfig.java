package com.cct.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.cct"})
@EnableJpaRepositories(basePackages = {"com.cct.repo"})
@EntityScan("com.cct.model")
public class ApplicationConfig {

	private  ApplicationConfig(){
        
    }

	@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
}
