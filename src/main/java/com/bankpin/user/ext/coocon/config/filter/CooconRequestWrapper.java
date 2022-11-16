package com.bankpin.user.ext.coocon.config.filter;

import com.bankpin.user.ext.coocon.model.dto.CooconLogDTO;
import com.bankpin.user.ext.coocon.service.CooconLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class CooconRequestWrapper extends HttpServletRequestWrapper {

    private ObjectMapper objectMapper;
    //private final String body;
    private byte[] rawData;
    private byte[] newData;


    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CooconRequestWrapper(HttpServletRequest request, CooconLogService cooconLogService) throws Exception {
        super(request);
        this.objectMapper = new ObjectMapper();
        StringBuilder stringBuilder = new StringBuilder();
        //objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        try {
            InputStream inputStream = request.getInputStream();
            this.rawData = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }

        //요청값 없을때 체크
        try {
            JSONObject jsonObject = readJSONStringFromRequestBody(rawData);
            String uri = request.getRequestURI();
            String apiType = (uri.substring(uri.lastIndexOf("/"))).replaceAll("/", "");

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(userDetails);
            CooconLogDTO.Create log = CooconLogDTO.Create.builder()
                    .custCiNo(userDetails.getUsername())
                    .apiUrl(uri)
                    .apiType(apiType)
                    .apiIpAddr(request.getRemoteAddr())
                    .apiInput(jsonObject.toJSONString())
                    .build();

            System.out.println(log);
            cooconLogService.create(log);
            jsonObject.put("LogId", log.getId());
            newData = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("fail:" + e);
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bis = new ByteArrayInputStream(newData);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return bis.available() == 0;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return bis.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public ServletRequest getRequest() {
        return super.getRequest();
    }


    public static JSONObject readJSONStringFromRequestBody(byte[] rawData) throws JsonProcessingException {
        StringBuffer json = new StringBuffer();
        ObjectMapper mapper = new ObjectMapper();
        String line = null;
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(rawData);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while((line = reader.readLine()) != null) {
                json.append(line);
            }

        }catch(Exception e) {
            log.info("Error reading JSON string: " + e.toString());
        }

        try {
            Map<String, String> map = mapper.readValue(json.toString(), Map.class);
            JSONObject jObj = new JSONObject(map);
            return jObj;
        } catch (Exception e) {
            log.error("fail: readJSONStringFromRequestBody " + e);
            return null;
        }
    }


}

