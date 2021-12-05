package com.example.manchesterunitedfan.web.interceptors;

import com.example.manchesterunitedfan.service.SecurityStatsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class SecurityStatsInterceptor implements HandlerInterceptor {
  private final SecurityStatsService securityStatsService;

    public SecurityStatsInterceptor(SecurityStatsService securityStatsService) {
        this.securityStatsService = securityStatsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        securityStatsService.onRequest();
        return true;
    }
}
