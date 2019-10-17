package com.simple.mmall.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author simple
 * @version 1.0
 * @date 2019-03-13 17:28
 * @since 1.0
 */
@Configuration
public class WebConfiguration {
    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        // 是否允许发送Cookie
                        .allowCredentials(true)
                        // 允许的自定义请求头
                        .allowedHeaders("*")
                        // 支持跨域请求的方法
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS");
                        // 配置响应的头信息
//                        .exposedHeaders("*");
            }
        };
    }
}
