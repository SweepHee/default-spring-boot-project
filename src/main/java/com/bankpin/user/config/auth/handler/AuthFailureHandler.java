package com.bankpin.user.config.auth.handler;

import com.bankpin.user.config.auth.model.type.AuthErrorType;
import org.springframework.security.authentication.*;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException
	{
	    String message = AuthErrorType.BAD_CREDENTIALS_EXCEPTION.getMessage();
	    if (exception instanceof DisabledException) {
        	message = AuthErrorType.DISABLED_EXCEPTION.getMessage();
        } else if (exception instanceof CredentialsExpiredException) {
        	message = AuthErrorType.CREDENTIALS_EXPIRED_EXCEPTION.getMessage();
        } else if (exception instanceof BadCredentialsException ) {
        	message = AuthErrorType.BAD_CREDENTIALS_EXCEPTION.getMessage();
        } else if (exception instanceof LockedException) {
			message = AuthErrorType.LOCKED_EXCEPTION.getMessage();
		} else if (exception instanceof AccountExpiredException) {
			message = AuthErrorType.ACCOUNT_EXPIRED_EXCEPTION.getMessage();
		}
		message = URLEncoder.encode(message, StandardCharsets.UTF_8);
	    setDefaultFailureUrl("/auth/login?error=true&message="+ message);
	    super.onAuthenticationFailure(request, response, exception);
	}

}
