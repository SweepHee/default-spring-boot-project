package com.bankpin.user.sns.auth.controller;


import com.bankpin.user.auth.model.dto.UserDTO;
import com.bankpin.user.auth.model.type.AuthErrorType;
import com.bankpin.user.auth.model.type.AuthorityType;
import com.bankpin.user.model.type.HttpCodeType;
import com.bankpin.user.sns.auth.model.dto.KakaoAuth;
import com.bankpin.user.sns.auth.model.dto.SnsUserDTO;
import com.bankpin.user.sns.auth.model.type.SnsType;
import com.bankpin.user.sns.auth.service.UserSnsAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
//@RestController
@Controller
@RequestMapping(value = "/sns/kakao")
@RequiredArgsConstructor
public class KakaoAuthController {
    private final UserSnsAuthService userSnsAuthService;

    @GetMapping("/access")
    public String accessKakao() {
        String uri = KakaoAuth.Property.getAccessCodeUri();
        // 카카오 로그인 -> 동의 -> 인가코드 받을 수 있음 앱에서 띄워야하는 화면
        return "redirect:" + uri;
    }

    @GetMapping("/oauth2/code")
    @ResponseBody
    public ResponseEntity<?> signUp(KakaoAuth.CallbackParam param) {

        if (param.getError() != null) {

            return ResponseEntity.ok().body(
                    UserDTO.ResponseMessage.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(param.getErrorDescription())
                            .build());
        }

        KakaoAuth.TokenResponse tokenRes = userSnsAuthService.getKakaoToken(param.getCode());
        String[] jwt = tokenRes.getIdToken().split("\\.");
        SnsUserDTO.Payload payload;

        try {
            payload = SnsUserDTO.Payload.base64decodeToPayload(jwt[1]);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    UserDTO.ResponseMessage.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(AuthErrorType.SIGN_UP_ERROR.getMessage())
                            .build());
        }

        SnsUserDTO.Create user = SnsUserDTO.Create.builder()
                .id(payload.getEmail())
                .password("")
                .custEmail(payload.getEmail())
                .custAuthCd(AuthorityType.USER.getKey())
                .custActvGbcd(Boolean.FALSE)
                .build();

        /* 이미 존재하는 회원일 경우 */
        SnsUserDTO.Create isUser = userSnsAuthService.duplicateCheck(user.getId(), SnsType.KAKAO);

        if (isUser == null) {
            userSnsAuthService.join(user, SnsType.KAKAO);
        }

        UserDetails userDetails = userSnsAuthService.loadUserByUsername(user.getId());

        return ResponseEntity.ok().body(
                UserDTO.ResponseMessage.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
//                        .data(userDetails)
                        .build());
    }


}
