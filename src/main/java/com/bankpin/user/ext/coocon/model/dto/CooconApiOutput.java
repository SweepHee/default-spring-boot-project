package com.bankpin.user.ext.coocon.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

public class CooconApiOutput {


    @Data
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Header {

        private List<String> date;
        private List<String> server;
        // Todo

    }

    @Data
    public static class Body {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loReqtNo;
        private String finEnMnNo;
        private String loPrdCd;
        private String bRpgUrl;
        private String cbTelNo;
        private String blank1;
        
        // response로 오는 object -> dto 변경
        public static CooconApiOutput.Body ObjectToDto(Object object) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(object);
            return mapper.readValue(jsonInString, CooconApiOutput.Body.class);
        }

    }



}
