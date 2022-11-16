package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

public class CooconLogDTO {

    @Data
    @Builder
    public static class Create {

        private Long id;
        private String custCiNo;
        private String apiUrl;
        private String apiType;
        private String apiIpAddr;
        private String apiInput;
        private String apiOutput;
        private String datetime;


    }

}
