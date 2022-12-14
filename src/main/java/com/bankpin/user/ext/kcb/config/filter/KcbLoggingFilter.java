package com.bankpin.user.ext.kcb.config.filter;

import com.bankpin.user.ext.kcb.service.KcbSmsLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class KcbLoggingFilter extends OncePerRequestFilter {

    private final KcbSmsLogService kcbSmsLogService;

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