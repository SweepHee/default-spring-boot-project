package com.bankpin.user.ext.coocon.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

public final class Util {

    private Util() {
        throw new UnsupportedOperationException();
    }

    // POST 요청
    public static ResponseEntity<Object> callPost(String reqUrl, JSONObject jsonObject) {

        //ResTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        //헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Content-Type", "application/json;charset=UTF-8");

        //url 생성
        URI url = URI.create(reqUrl);
        ResponseEntity<Object> response = null;

        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toJSONString(), headers);
        response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);

        //GET으로 보내는 경우 : 쿼리파라미터는 url에 붙여 보내면 댐
//        RequestEntity<String> req = new RequestEntity<>(headers, HttpMethod.GET, url);
//        ResponseEntity<String> res = restTemplate.exchange(req, String.class);

        return response;
    }

    
    // dto를 파스칼케이스 JSON형태로 반환
    public static <T> JSONObject dtoToJsonObjectPascal(T t) throws JsonProcessingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        String jsonStr = objectMapper.writeValueAsString(t);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse( jsonStr );
        return (JSONObject) obj;
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    // 랜덤 두 자리 숫자 문자열로 리턴
    public static String random2NumToStr() {
        int min = 10;
        int max = 100;
        int random = (int) ((Math.random() * (max-min)) + min);
        return String.valueOf(random);
    }

    public static String random3NumToStr() {
        int min = 100;
        int max = 1000;
        int random = (int) ((Math.random() * (max-min)) + min);
        return String.valueOf(random);
    }

    
    // 객체를 json string으로 반환
    public static String ObjToJson(Object obj) {

        if (obj == null) return null;

        ObjectMapper mapper = new ObjectMapper();
        String res = null;

        try {
            res =  mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) { e.printStackTrace(); }

        return res;
    }



    public static String getMD5(String str) {
        String MD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            MD5 = null;
        }
        return MD5;
    }

    
    /* letter만큼 랜덤 숫자 문자열 만들어줌 */
    public static String randomNumberToString(int letter) {

        Random random = new Random();
        int createNum = 0;
        String ranNum = "";
        StringBuilder resultNum = new StringBuilder();

        for (int i=0; i<letter; i++) {

            createNum = random.nextInt(9);
            ranNum =  Integer.toString(createNum);
            resultNum.append(ranNum);
        }

        return resultNum.toString();

    }




}