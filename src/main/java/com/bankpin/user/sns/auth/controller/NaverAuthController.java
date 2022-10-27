package com.bankpin.user.sns.auth.controller;

import com.bankpin.user.auth.model.dto.UserDTO;
import com.bankpin.user.auth.model.type.AuthErrorType;
import com.bankpin.user.auth.model.type.AuthorityType;
import com.bankpin.user.model.type.HttpCodeType;
import com.bankpin.user.sns.auth.model.dto.NaverAuth;
import com.bankpin.user.sns.auth.model.dto.SnsUserDTO;
import com.bankpin.user.sns.auth.model.type.SnsType;
import com.bankpin.user.sns.auth.service.UserSnsAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
//@RestController
@Controller
@RequestMapping(value = "/sns/naver")
@RequiredArgsConstructor
public class NaverAuthController {
    private final UserSnsAuthService userSnsAuthService;

    @GetMapping(value="")
    public String index () {
        return "naverlogintest";
    }


    @GetMapping("/access")
    public String access() {
        String urlEncoded = URLEncoder.encode(NaverAuth.Property.redirectUri, StandardCharsets.UTF_8);
        return "redirect:https://nid.naver.com/oauth2.0/authorize?client_id="+NaverAuth.Property.clientId+"&response_type=code&redirect_uri="+urlEncoded+"&state=123";
    }


    @GetMapping("/logged2")
    public String logged2(Authentication authentication) {
        System.out.println(authentication.getName());
        System.out.println("authentication: " + authentication);
        return "naverlogintest";
    }

    @GetMapping("/oauth2/code")
    @ResponseBody
    public ResponseEntity<?> signUp(NaverAuth.CallbackParam param) {

        if (param.getError() != null) {

            return ResponseEntity.internalServerError().body(
                    UserDTO.ResponseMessage.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(param.getErrorDescription())
                            .build());

        }

        NaverAuth.TokenReq tokenReq = NaverAuth.TokenReq.builder()
                .grantType("authorization_code")
                .code(param.getCode())
                .state(param.getState())
                .build();

        NaverAuth.TokenRes tokenRes = userSnsAuthService.getNaverAccessToken(tokenReq);
        NaverAuth.Profile profile = userSnsAuthService.getNaverUserProfile(tokenRes);

        SnsUserDTO.Create user = SnsUserDTO.Create.builder()
                .id(profile.getResponse().getEmail())
                .password("")
                .custEmail(profile.getResponse().getEmail())
                .custAuthCd(AuthorityType.USER.getKey())
                .custActvGbcd(Boolean.FALSE)
                .build();

        /* 이미 존재하는 회원일 경우 */
        SnsUserDTO.Create isUser = userSnsAuthService.duplicateCheck(user.getId(), SnsType.NAVER);

        if (isUser == null) {
            userSnsAuthService.join(user, SnsType.NAVER);
        }

        UserDetails userDetails = userSnsAuthService.loadUserByUsername(user.getId());

        return ResponseEntity.ok().body(
                UserDTO.ResponseMessage.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .build());
    }



    /* 로그인 테스트하기 */
    @GetMapping("/test1")
    @ResponseBody
    public UserDetails test1 () {
        UserDetails userDetails = userSnsAuthService.loadUserByUsername("jeonsh1991@naver.com");
        return userDetails;
    }
    
    /* 로그인됐는지 확인하기 /user/auth/login으로도 확인가능 */
    @GetMapping("/test2")
    @ResponseBody
    public Authentication test2(Authentication authentication) {
        return authentication;
    }










}