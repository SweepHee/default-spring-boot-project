package com.bankpin.user.auth.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.auth.model.dto.UserDTO;
import com.bankpin.user.auth.model.type.AuthErrorType;
import com.bankpin.user.auth.model.type.AuthorityType;
import com.bankpin.user.auth.service.UserAuthService;
import com.bankpin.user.model.type.HttpCodeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthController
{
    @Autowired
    private UserAuthService userAuthService;

    @GetMapping(value = "/login")
    public ResponseEntity<UserDTO.ResponseMessage> login(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        boolean authenticate = Boolean.FALSE;
        boolean error = Boolean.parseBoolean(request.getParameter("error"));
        String message = request.getParameter("message");
        if (authentication != null) {
            authenticate = authentication.isAuthenticated();
        } else {
            if (StringUtils.hasLength(message)) {
                message = AuthErrorType.UNAUTHENTICATED.getKey() +", " + message;
            } else {
                message = AuthErrorType.UNAUTHENTICATED.getKey();
            }
        }
        return ResponseEntity.ok().body(
                UserDTO.ResponseMessage.builder()
                    .error(!authenticate | error)
                    .code(authenticate ? HttpCodeType.OK.getCode() : HttpCodeType.UNAUTHORIZED.getCode())
                    .message(message)
                    .build());
    }

    @GetMapping(value = "/logout/success")
    public ResponseEntity<UserDTO.ResponseMessage> loout(@RequestParam("authentication") Boolean authentication)
    {
        return ResponseEntity.ok().body(
                UserDTO.ResponseMessage.builder()
                    .error(!authentication)
                    .code(authentication ? HttpCodeType.OK.getCode() : HttpCodeType.UNAUTHORIZED.getCode())
                    .build());
    }

    @PostMapping(value = "/signUp")
	public ResponseEntity<UserDTO.ResponseMessage> create(UserDTO.Create user)
    {
        boolean error = false;
        int code = HttpCodeType.OK.getCode();
        String message = null;

        if (!user.getPassword().equals(user.getRePassword())) {
            return ResponseEntity.ok().body(
                    UserDTO.ResponseMessage.builder()
                    .error(true)
                    .code(HttpCodeType.BAD_REQUEST.getCode())
                    .message(AuthErrorType.PASSWORD_NOT_FOUND.getMessage())
                    .build());
        }

        UserAuth userAuth = userAuthService.selectUser(user.getId());
        if (userAuth != null) {
            return ResponseEntity.ok().body(
                    UserDTO.ResponseMessage.builder()
                    .error(true)
                    .code(HttpCodeType.FORBIDDEN.getCode())
                    .message(AuthErrorType.USER_ALREADY_EXISTS.getMessage())
                    .build());
        }

        user.setCustAuthCd(AuthorityType.USER.getKey());
        user.setCustActvGbcd(Boolean.FALSE);
        try {
            userAuthService.join(user);
        } catch (Exception e) {
            error = true;
            code = HttpCodeType.INTERNAL_SERVER_ERROR.getCode();
            message = AuthErrorType.SIGN_UP_ERROR.getMessage();
            log.error("userAuthService.join: "+ e.getMessage());
        }

        return ResponseEntity.ok().body(
                UserDTO.ResponseMessage.builder()
                    .error(error)
                    .code(code)
                    .message(message)
                    .build());
	}

}
