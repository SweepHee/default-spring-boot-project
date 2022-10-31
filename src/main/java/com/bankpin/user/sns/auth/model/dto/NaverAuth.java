package com.bankpin.user.sns.auth.model.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class NaverAuth {


    @Component @Getter    public static class Property {
        public static String tokenUri;
        @Value("${spring.security.oauth2.client.provider.naver.token_uri}")
        private void setTokenUri(String tokenUris) {
            tokenUri = tokenUris;
        }

        public static String clientId;
        @Value("${spring.security.oauth2.client.registration.naver.client-id}")
        private void setClientId(String clientIds) {
            clientId = clientIds;
        }

        public static String clientSecret;
        @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
        private void setClientSecret(String clientSecrets) {
            clientSecret = clientSecrets;
        }

        public static String redirectUri;
        @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
        private void setRedirectUri(String redirectUris) {
            redirectUri = redirectUris;
        }

        public static String userInfoUri;
        @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
        private void setUserInfoUri(String userInfoUris) {
            userInfoUri = userInfoUris;
        }



        // 토큰요청주소 리턴
        public static String getAccessTokenUri(TokenReq tokenReq) {

            return tokenUri+
                    "?grant_type="+tokenReq.getGrantType()+
                    "&code="+tokenReq.getCode()+
                    "&client_secret="+tokenReq.getClientSecret()+
                    "&client_id="+tokenReq.getClientId()+
                    "&state="+tokenReq.getState();
        }

    }

    @Data
    public static class CallbackParam {

        private String code;
        private String state;
        private String error;
        private String errorDescription;

        public static JSONObject toJsonObject(CallbackParam param) throws JsonProcessingException, ParseException {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(param);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse( jsonStr );
            return (JSONObject) obj;
        }

    }

    @Data
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TokenReq {
        private String grantType;

        @Builder.Default
        private String clientId = Property.clientId;

        @Builder.Default
        private String clientSecret = Property.clientSecret;

        private String code;

        private String state;

        private String refreshToken;
        private String accessToken;

        @Builder.Default
        private String serviceProvider = "NAVER";

        public static JSONObject toJsonObject(TokenReq param) throws JsonProcessingException, ParseException {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(param);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse( jsonStr );
            return (JSONObject) obj;
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TokenRes {

        private String accessToken;
        private String refreshToken;
        private String tokenType;
        private String expiresIn;
        private String error;
        private String errorDescription;

        public static TokenRes toTokenRes(Object object) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(object);
            return mapper.readValue(jsonInString, TokenRes.class);
        }

    }

    @Data
    public static class TokenRefresh {

        private String grantType;
        private String clientId;
        private String clientSecret;
        private String code;
        private String state;
        private String refreshToken;
        private String accessToken;
        private String serviceProvider;

    }

    @Data
    public static class Profile {

        private String resultcode;
        private String message;
        private Response response;

        @Data
        public static class Response {

            private String id;
            private String nickname;
            private String name;
            private String email;
            private String gender;
            private String age;
            private String birthday;
            private String profileImage;
            private String birthyear;
            private String mobile;

        }

    }

}
