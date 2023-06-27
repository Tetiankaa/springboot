package com.example.springboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc //To enable Spring MVC support through a Java configuration class, we just add the @EnableWebMvc annotation:
@Configuration
//By annotating a class with @Configuration, you indicate to Spring that this class contains configuration information
// and that Spring should process it accordingly.
public class WebConfig implements WebMvcConfigurer {  //If we want to customize this configuration, we need to implement the WebMvcConfigurer interface:
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //specifying static resource location for photo related files
        //зберігання файлу в статичній системі
       String path = "file:///" + System.getProperty("user.home")+ File.separator;

       registry
               .addResourceHandler( "/photo/**")
               .addResourceLocations(path);
    }
}
