package com.bankpin.user.ext.kcb.model.dto;

import com.bankpin.user.ext.kcb.model.type.ResultCodeType;
import kcb.org.json.JSONObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PassAppCertDTO {

    @Data
    @Builder
    public static class Param {

        private String cpCd;

        @NotNull
        @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "거래고유번호에 유효하지 않은 문자열이 있습니다.")
        private String txSeqNo;

    }


    @Data
    @Builder
    public static class ReturnData {

        private ResultCodeType rsltCd;
        private String rsltMsg;
        private String cpCd;
        private String txSeqNo;
        private String di;
        private String ci;
        private String ciUpdate;

        public static ReturnData toReturnData(JSONObject resJson) {

            return ReturnData.builder()
                    .rsltCd(ResultCodeType.of(resJson.getString("RSLT_CD")))
                    .rsltMsg(resJson.getString("RSLT_MSG"))
                    .cpCd(resJson.getString("CP_CD"))
                    .txSeqNo(resJson.getString("TX_SEQ_NO"))
                    .di(resJson.getString("DI"))
                    .ci(resJson.getString("CI"))
                    .ciUpdate(resJson.getString("CI_UPDATE"))
                    .build();

        }
    }

}
