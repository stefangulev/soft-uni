package com.example.manchesterunitedfan.service.impl;

import com.example.manchesterunitedfan.service.UsageStatsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class UsageStatsServiceImpl implements UsageStatsService {
    public int squadRequests;
    public int standingsRequests;
    public int matchesRequests;
    public int newsRequests;
    public int stadiumRequests;
    public int storeRequests;

    @Override
    public void onRequest(HttpServletRequest request) {
        String reqURL = request.getRequestURL().toString();

        if(reqURL.contains("squad")) {
            squadRequests++;
        } else if(reqURL.contains("standings")) {
            standingsRequests++;
        } else if(reqURL.contains("matches")) {
            matchesRequests++;
        } else if(reqURL.contains("news")) {
            newsRequests++;
        } else if (reqURL.contains("stadium")) {
            stadiumRequests++;
        } else if(reqURL.contains("store")) {
            storeRequests++;
        }
    }
}
