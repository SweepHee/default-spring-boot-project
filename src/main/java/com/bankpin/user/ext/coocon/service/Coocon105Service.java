package com.bankpin.user.ext.coocon.service;


import com.bankpin.user.ext.coocon.config.CooconPropertyConfig;
import com.bankpin.user.ext.coocon.model.dto.ExecInfoDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconExecInfoMapper;
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
public class Coocon105Service {

    private final CooconPropertyConfig cooconPropertyConfig;
    private final CooconExecInfoMapper cooconExecInfoMapper;


    public ExecInfoDTO.RequestParams request(ExecInfoDTO.ResponseParams param) throws ParseException, JsonProcessingException {
        
        JSONObject jsonReq = Util.dtoToJsonObjectPascal(param);

        return WebClient
                .create()
                .post()
                .uri(cooconPropertyConfig.getUri(param.getCommon().getApiNm()))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonReq)
                .retrieve()
                .bodyToMono(ExecInfoDTO.RequestParams.class)
                .block();
    }

    public void upsert(ExecInfoDTO.RequestParams param) {

        ExecInfoDTO.Create create = ExecInfoDTO.Create.builder()
                .lnReqNo(param.getLoAplcMmNo())
                .fintecOrgMngno(param.getAlncIsMnNo())
                .lnReqYn("Y") //
                .lnReqDttm(param.getAplyDt())
//                .bankCd()  // 은행코드 없음
//                .bankBrchCd() // 은행지점코드
                .lnPrdtCd(param.getLoPrdCd())
//                .lnPrdtNm() // 상품명 없음
                .lnAmt(param.getLoReqAmt())
                .lstLnLmtAmt(param.getLtLoLmAmt())
                .lnRateKindGbcd(param.getLtLoRt())
                .strdRate(param.getBsRt())
                .applyRate(param.getApRt())
                .priRateRsnCntn(param.getPrnlRsn())
                .priLmtAmt(param.getPrnlLmAmt())
                .priRate(param.getPrnlRt())
                .lnReqDt(param.getAplyDt())
//                .lnRateCycleCd() // 대출금리주기코드. 알수없음
                .lnAuthDt(param.getLoCtDt())
                .build();

        if (cooconExecInfoMapper.existsByLnReqNoAndFintecOrgMngnoAndLnPrdtCd(create)) {
            cooconExecInfoMapper.update(create);
        } else {
            cooconExecInfoMapper.save(create);
        }

    }
}
