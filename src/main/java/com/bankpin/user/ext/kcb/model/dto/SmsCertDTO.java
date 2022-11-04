package com.bankpin.user.ext.kcb.model.dto;


import com.bankpin.user.ext.kcb.model.type.ResultCodeType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kcb.org.json.JSONObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SmsCertDTO {

    @Data
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Param {

        @NotNull
        @Pattern(regexp = "^[0-9]+$", message = "SMS인증번호에 유효하지 않은 문자열이 있습니다.")
        private String otpNo;

        private String cpCd;

        @NotNull
        @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "거래고유번호에 유효하지 않은 문자열이 있습니다.")
        private String txSeqNo;

        @NotNull
        @Pattern(regexp = "^[0-9]+$", message = "휴대폰번호에 유효하지 않은 문자열이 있습니다.")
        private String telNo;

    }

    @Data
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ReturnData {

        private ResultCodeType rsltCd;
        private String rsltMsg;
        private String cpCd;
        private String txSeqNo;
        private String di;
        private String ci;
        private String ci2;
        private String ciUpdate;
        private String rsltName;
        private String telNo;
        private String telComCd;
        private String rsltBirthday;
        private String rsltSexCd;
        private String rsltNtvFrnrCd;

        public static ReturnData toReturnData(JSONObject jsonObject) {
            return ReturnData.builder()
                    .rsltCd(ResultCodeType.of(jsonObject.getString("RSLT_CD")))
                    .rsltMsg(jsonObject.has("RSLT_MSG") ? jsonObject.getString("RSLT_MSG") : null)
                    .txSeqNo(jsonObject.has("TX_SEQ_NO") ? jsonObject.getString("TX_SEQ_NO") : null)
                    .di(jsonObject.has("DI") ? jsonObject.getString("DI") : null)
                    .ci(jsonObject.has("CI") ? jsonObject.getString("CI") : null)
                    .ciUpdate(jsonObject.has("CI_UPDATE") ? jsonObject.getString("CI_UPDATE") : null)
                    .rsltName(jsonObject.has(("RSLT_NAME")) ? jsonObject.getString(("RSLT_NAME")) : null)
                    .telNo(jsonObject.has("TEL_NO") ? jsonObject.getString("TEL_NO") : null)
                    .telComCd(jsonObject.has("TEL_COM_CD") ? jsonObject.getString("TEL_COM_CD") : null)
                    .rsltBirthday(jsonObject.has("RSLT_BIRTHDAY") ? jsonObject.getString("RSLT_BIRTHDAY") : null)
                    .rsltSexCd(jsonObject.has(("RSLT_SEX_CD")) ? jsonObject.getString(("RSLT_SEX_CD")) : null)
                    .rsltNtvFrnrCd(jsonObject.has(("RSLT_NTV_FRNR_CD")) ? jsonObject.getString(("RSLT_NTV_FRNR_CD")) : null)
                    .build();
        }
    }

}
