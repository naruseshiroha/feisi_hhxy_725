package com.example.config;

import javax.annotation.Resource;

import com.example.interceptor.LoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 这里匹配了所有的URL，允许所有的外域发起跨域请求，允许外域发起请求任意HTTP Method，允许跨域请求包含任意的头信息。
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .maxAge(3600); // 一小时内不需要再预检
    }

    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/test/**",
                        "/redis/**",
                        "/user/login",
                        "/category/all",
                        "/ebook/findAllEbook",
                        "/doc/all/**",
                        "/doc/vote/**",
                        "/ebook-snapshot/**",
                        "/doc/find-content/**");
                        // "/ws/**",
    }

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("/**")
    // .addResourceLocations("classpath:/static/")
    // .addResourceLocations("classpath:/templates/");
    // // 映射图片保存地址
    // //
    // registry.addResourceHandler("/img/**").addResourceLocations("file:D:/bysj/pljyd/src/main/resources/static/img/");
    // }

    /**
     * 拦截器
     */
    // @Resource
    // LogInterceptor logInterceptor;

    // public void addInterceptors(InterceptorRegistry registry) {
    // registry.addInterceptor(logInterceptor)
    // .addPathPatterns("/**")
    // .excludePathPatterns("/login");
    // }

}
