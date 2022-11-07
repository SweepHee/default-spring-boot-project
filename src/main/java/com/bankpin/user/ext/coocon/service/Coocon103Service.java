package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.ext.coocon.model.dto.CooconDTO;
import com.bankpin.user.ext.coocon.model.dto.ReqLstDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconReqLstMapper;
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
public class Coocon103Service {

    private final CooconReqLstMapper cooconReqLstMapper;


    public CooconDTO.Output request(ReqLstDTO.RequestParams param) throws ParseException, JsonProcessingException {

        JSONObject jsonReq = Util.dtoToJsonObjectPascal(param);

        return WebClient
                .create()
                .post()
                .uri(ApiProperties.requestURI(ApiProperties.ADL_103_IQ))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonReq)
                .retrieve()
                .bodyToMono(CooconDTO.Output.class)
                .block();

    }

    public void save(CooconDTO.Output body) {

        ReqLstDTO.Create create = ReqLstDTO.Create.builder()
                .lnReqNo(body.getLoReqtNo())
                .fintecOrgMngno(body.getFinEnMnNo())
//                .bankCd() // 은행코드 없음
//                .bankBrchCd() // 은행지점코드
                .lnPrdtCd(body.getLoPrdCd())
                .brPageUrl(body.getBRpgUrl())
                .callbackTelNo(body.getCbTelNo())
                .build();

        cooconReqLstMapper.save(create);

    }
}
