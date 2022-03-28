package com.example.statistics.filters;

import com.example.statistics.model.StatisticsClient;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class StatisticsFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain,String path) throws ServletException, IOException {
        HttpSession hs = ((HttpServletRequest)request).getSession();
        StatisticsClient statisticsClient = (StatisticsClient) hs.getAttribute("statisticsClient");
        statisticsClient.сlientToPage(path);
        statisticsClient.requestClient();
    }
}
