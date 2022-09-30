package com.bankpin.user.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler
{
    @Override
    public void onLogoutSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        Boolean authenticate = Boolean.FALSE;
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            authenticate = Boolean.TRUE;
        }
        response.sendRedirect("/user/auth/logout/success?authentication="+ authenticate);
        super.onLogoutSuccess(request, response, authentication);
    }

}
