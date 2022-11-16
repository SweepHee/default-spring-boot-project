package com.bankpin.user.ext.kcb.config.filter;

import com.bankpin.user.ext.coocon.config.filter.CooconRequestWrapper;
import com.bankpin.user.ext.coocon.service.CooconLogService;
import com.bankpin.user.ext.kcb.service.KcbSmsLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/api/v1/kcb/sms/*"})
public class KcbLoggingFilter extends OncePerRequestFilter {

    @Autowired
    private KcbSmsLogService kcbSmsLogService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        final HttpServletRequest requestWrapper;
        try {
            requestWrapper = new KcbRequestWrapper(req, kcbSmsLogService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(requestWrapper, res);

    }

}
