package com.example.statistics.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class IndexFilter extends StatisticsFilter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilter(request,response,chain,"Main");
        chain.doFilter(request, response);
    }
}
