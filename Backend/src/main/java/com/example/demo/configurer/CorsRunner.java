package com.example.demo.configurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsRunner implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080/")
                .allowedHeaders("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowCredentials(true);
    }
}
