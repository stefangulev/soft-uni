package com.example.manchesterunitedfan.web.interceptors;

import com.example.manchesterunitedfan.service.UsageStatsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UsageStatsInterceptor implements HandlerInterceptor {
    private final UsageStatsService usageStatsService;

    public UsageStatsInterceptor(UsageStatsService usageStatsService) {
        this.usageStatsService = usageStatsService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        usageStatsService.onRequest(request);
    }
}
