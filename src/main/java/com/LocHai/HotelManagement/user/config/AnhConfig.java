package com.LocHai.HotelManagement.user.config;


import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class AnhConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("D:\\project_website\\backend\\HotelManagement\\src\\main\\resources\\static\\images\\");
    }
}
