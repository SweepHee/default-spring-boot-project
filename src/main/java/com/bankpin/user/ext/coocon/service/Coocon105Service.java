package com.bankpin.user.ext.coocon.service;


import com.bankpin.user.ext.coocon.model.dto.ExecInfoDTO;
import com.bankpin.user.ext.coocon.model.type.ApiProperties;
import com.bankpin.user.ext.coocon.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class Coocon105Service {


    public ExecInfoDTO.RequestParams request(ExecInfoDTO.ResponseParams param) throws ParseException, JsonProcessingException {
        
        JSONObject jsonReq = Util.dtoToJsonObjectPascal(param);

        return WebClient
                .create()
                .post()
                .uri(ApiProperties.requestURI(ApiProperties.ADL_105_IQ))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonReq)
                .retrieve()
                .bodyToMono(ExecInfoDTO.RequestParams.class)
                .block();
    }

}
