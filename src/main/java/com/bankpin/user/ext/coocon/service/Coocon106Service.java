package com.bankpin.user.ext.coocon.service;


import com.bankpin.user.ext.coocon.model.dto.ExecInfoDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconExecInfoMapper;
import com.bankpin.user.ext.coocon.model.type.ApiProperties;
import com.bankpin.user.ext.coocon.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class Coocon106Service {

    private final CooconExecInfoMapper cooconExecInfoMapper;


    public ExecInfoDTO.CancelParams request(ExecInfoDTO.CancelParams param) throws ParseException, JsonProcessingException {

        JSONObject jsonReq = Util.dtoToJsonObjectPascal(param);

        return WebClient
                .create()
                .post()
                .uri(ApiProperties.requestURI(ApiProperties.ADL_106_IF))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonReq)
                .retrieve()
                .bodyToMono(ExecInfoDTO.CancelParams.class)
                .block();

    }

    public void cancel(ExecInfoDTO.CancelParams param) {
        ExecInfoDTO.Create cancel = ExecInfoDTO.Create.builder()
                .lnReqNo(param.getLoAplcMmNo())
                .lnCnslYn("Y")
                .build();
        System.out.println(cancel);
        System.out.println("시작=========");
        cooconExecInfoMapper.cancel(cancel);
    }

    public boolean isCancel(ExecInfoDTO.CancelParams param) {
        ExecInfoDTO.Create cancel = ExecInfoDTO.Create.builder()
                .lnReqNo(param.getLoAplcMmNo())
                .lnCnslYn("Y")
                .build();
        return cooconExecInfoMapper.isCancel(cancel);
    }
}
