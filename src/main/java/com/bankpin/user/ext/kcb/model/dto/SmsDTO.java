package com.bankpin.user.ext.kcb.model.dto;

import com.bankpin.user.ext.kcb.model.type.ResultCodeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kcb.org.json.JSONObject;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SmsDTO {

    @Data
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Param {

        private String cpCd;

        @NotNull
        @Pattern(regexp = "^[가-힝a-zA-Z ]*", message = "성명에 유효하지 않은 문자열이 있습니다.")
        private String name;

        @NotNull
        @Pattern(regexp = "^[0-9x]*", message = "생년월일에 유효하지 않은 문자열이 있습니다.")
        private String birthday;

        @NotNull
        @Pattern(regexp = "^[MFx]", message = "성별에 유효하지 않은 문자열이 있습니다.")
        private String sexCd;

        @NotNull
        @Pattern(regexp = "^[LFx]", message = "내외국인구분에 유효하지 않은 문자열이 있습니다.")
        private String ntvFrnrCd;

        @NotNull
        @Pattern(regexp = "^(01|02|03|04|05|06)$", message = "통신사코드에 유효하지 않은 문자열이 있습니다.")
        private String telComCd;

        @NotNull
        @Pattern(regexp = "^[0-9]*", message = "휴대폰번호에 유효하지 않은 문자열이 있습니다.")
        private String telNo;

        @NotNull
        @Pattern(regexp = "^(00|01|02|03|04|99|EC)$", message = "인증요청사유코드에 유효하지 않은 문자열이 있습니다.")
        private String rqstCausCd;

        @NotNull
        @Pattern(regexp = "^[YN]", message = "재전송여부에 유효하지 않은 문자열이 있습니다.")
        private String smsResendYn;

        @Pattern(regexp = "^[0-9a-zA-Z]*", message = "거래고유번호에 유효하지 않은 문자열이 있습니다.")
        private String txSeqNo; // 거래일련번호. 모듈에서 자동으로 채번
        
        private String appHashStr; // 구글 SMS retrieve 사용시

        @NotNull
        @Pattern(regexp = "^Y$", message = "동의여부1에 동의하지 않았습니다.")
        private String agree1;

        @NotNull
        @Pattern(regexp = "^Y$", message = "동의여부2에 동의하지 않았습니다.")
        private String agree2;

        @NotNull
        @Pattern(regexp = "^Y$", message = "동의여부3에 동의하지 않았습니다.")
        private String agree3;

        @NotNull
        @Pattern(regexp = "^Y$", message = "동의여부4에 동의하지 않았습니다.")
        private String agree4;

    }


    @Data
    @Builder
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class ReturnData
    {
        private ResultCodeType rsltCd;
        private String rsltMsg;
        private String cpCd;
        private String txSeqNo;
        private String telComCd;
        private String telComResCd;
        private String resendCnt;

        private String telNo; // return data에 없어서 직접 넣어줬음. 앱으로 넘겨줄때 필요

        public static ReturnData toReturnData(JSONObject jsonObject) {

            return ReturnData.builder()
                    .rsltCd(ResultCodeType.of(jsonObject.getString("RSLT_CD")))
                    .rsltMsg(jsonObject.has("RSLT_MSG") ? jsonObject.getString("RSLT_MSG") : null)
                    .cpCd(jsonObject.has("CP_CD") ? jsonObject.getString("CP_CD") : null)
                    .txSeqNo(jsonObject.has("TX_SEQ_NO") ? jsonObject.getString("TX_SEQ_NO") : null)
                    .telComCd(jsonObject.has("TEL_COM_CD")? jsonObject.getString("TEL_COM_CD") : null)
                    .telComResCd(jsonObject.has("TEL_COM_RES_CD") ? jsonObject.getString("TEL_COM_RES_CD") : null)
                    .resendCnt(jsonObject.has("RESEND_CNT") ? jsonObject.getString("RESEND_CNT") : null)
                    .telNo(jsonObject.has("TEL_NO") ? jsonObject.getString("TEL_NO") : null)
                    .build();

        }
    }


}
