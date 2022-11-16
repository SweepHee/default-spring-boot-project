package com.bankpin.user.ext.coocon.config.filter;

import com.bankpin.user.ext.coocon.service.CooconLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/api/v1/coocon/*", "/coocon/callback/*"})
public class CooconLoggingFilter extends OncePerRequestFilter {

    @Autowired
    private CooconLogService cooconLogService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        final HttpServletRequest requestWrapper;
        try {
            requestWrapper = new CooconRequestWrapper(req, cooconLogService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(requestWrapper, res);

    }

}
