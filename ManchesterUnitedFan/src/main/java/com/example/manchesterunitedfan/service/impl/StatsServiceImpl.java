package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {
    public int anonymousRequests;
    public int authenticatedRequests;
    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authenticatedRequests++;
        } else {
            anonymousRequests++;
        }
    }
}
