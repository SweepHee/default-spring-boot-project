package com.bankpin.user.sns.auth.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Slf4j
public class KakaoAuth {


    @Component
    public static class Property {

        public static String clientId; // rest api key와 동일

        @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
        private void setClientId(String clientIds) {
            clientId = clientIds;
        }

        public static String redirectUri;
        @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
        private void setRedirectUri(String redirectUris) {
            redirectUri = redirectUris;
        }

        public static String authorizationGrantType;
        @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
        private void setAuthorizationGrantType(String authorizationGrantTypes) {
            authorizationGrantType = authorizationGrantTypes;
        }

        public static String authorizationUri;
        @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
        private void setAuthorizationUri(String authorizationUris) {
            authorizationUri = authorizationUris;
        }

        public static String tokenUri;
        @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
        private void setTokenUri(String tokenUris) {
            tokenUri = tokenUris;
        }
        @Setter
        private String code;


        public static String getAccessCodeUri() {
            return authorizationUri +
                    "?client_id=" + clientId +
                    "&redirect_uri=" + redirectUri +
                    "&response_type=code";
        }

        public static String getTokenUriWithParam(String code) {
            return tokenUri +
                    "?grant_type=" + authorizationGrantType +
                    "&client_id=" + clientId +
                    "redirect_uri=" + redirectUri +
                    "code=" + code
                    ;
        }

        public static Property getTokenBody(String code) {
            Property property = new Property();
            property.setCode(code);
            return property;
        }

        public static MultiValueMap<String, String> toMultiValueMap(String code) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", Property.authorizationGrantType);
            params.add("client_id", Property.clientId);
            params.add("redirect_uri", Property.redirectUri);
            params.add("code", code);
            return params;
        }

    }

    @Data
    public static class CallbackParam {

        private String code;
        private String state;
        private String error;
        private String errorDescription;

    }

    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TokenResponse {
        private String tokenType;
        private String accessToken;
        private String idToken;
        private String expiresIn;
        private String refreshToken;
        private String refreshTokenExpiresIn;
        private String scope;

    }



}