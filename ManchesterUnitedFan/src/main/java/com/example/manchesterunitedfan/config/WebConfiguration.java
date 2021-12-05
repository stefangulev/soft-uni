package com.example.manchesterunitedfan.config;

import com.example.manchesterunitedfan.web.interceptors.SecurityStatsInterceptor;
import com.example.manchesterunitedfan.web.interceptors.UsageStatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final SecurityStatsInterceptor securityStatsInterceptor;
    private final UsageStatsInterceptor usageStatsInterceptor;

    public WebConfiguration(SecurityStatsInterceptor securityStatsInterceptor, UsageStatsInterceptor usageStatsInterceptor) {
        this.securityStatsInterceptor = securityStatsInterceptor;
        this.usageStatsInterceptor = usageStatsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityStatsInterceptor);
        registry.addInterceptor(usageStatsInterceptor);
    }
}
