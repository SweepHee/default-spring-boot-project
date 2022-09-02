package com.bankpin.user.config.auth.controller;

import com.bankpin.user.config.auth.model.dto.CustomerAuth;
import com.bankpin.user.config.auth.model.dto.CustomerDTO;
import com.bankpin.user.config.auth.model.type.AuthErrorType;
import com.bankpin.user.config.auth.model.type.AuthorityType;
import com.bankpin.user.config.auth.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Controller
@RequestMapping(value = "/auth")
public class AuthController
{
    @Autowired
    private UserAuthService userAuthService;

    @ResponseBody
    @GetMapping(value = "/login")
    public ResponseEntity<CustomerDTO.ResponseMessage> login(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        boolean authenticate = Boolean.FALSE;
        boolean error = Boolean.parseBoolean(request.getParameter("error"));
        String message = request.getParameter("message");
        if (authentication != null) {
            authenticate = authentication.isAuthenticated();
        }
        return ResponseEntity.ok().body(
                CustomerDTO.ResponseMessage.builder()
                    .error(!authenticate | error)
                    .message(message)
                    .build());
    }

    @ResponseBody
    @GetMapping(value = "/logout/success")
    public ResponseEntity<CustomerDTO.ResponseMessage> loout(@RequestParam("authentication") Boolean authentication)
    {
        return ResponseEntity.ok().body(
                CustomerDTO.ResponseMessage.builder()
                    .error(!authentication)
                    .build());
    }


    /**
     * HTML
     */
    @GetMapping(value = "/signUp")
    public String signUp(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception)
    {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "auth/signup";
    }


    @ResponseBody
    @PostMapping(value = "/signUp")
	public ResponseEntity<CustomerDTO.ResponseMessage> create(CustomerDTO.Create user)
    {
        boolean error = false;
        String message = null;

        if (!user.getPassword().equals(user.getRePassword())) {
            return ResponseEntity.ok().body(
                    CustomerDTO.ResponseMessage.builder()
                    .error(true)
                    .message(AuthErrorType.PASSWORD_NOT_FOUND.getMessage())
                    .build());
        }

        CustomerAuth userAuth = userAuthService.selectUser(user.getId());
        if (userAuth != null) {
            return ResponseEntity.ok().body(
                    CustomerDTO.ResponseMessage.builder()
                    .error(true)
                    .message(AuthErrorType.USER_ALREADY_EXISTS.getMessage())
                    .build());
        }

        user.setAuthority(AuthorityType.USER.getKey());
        try {
            userAuthService.join(user);
        } catch (Exception e) {
            error = true;
            message = AuthErrorType.SIGN_UP_ERROR.getMessage();
            log.error("userAuthService.join: "+ e.getMessage());
        }

        return ResponseEntity.ok().body(
                CustomerDTO.ResponseMessage.builder()
                    .error(error)
                    .message(message)
                    .build());
	}

}
