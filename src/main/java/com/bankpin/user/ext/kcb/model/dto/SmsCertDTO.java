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
        private String ci;
        private String di;
        private String ciUpdate;

        public static ReturnData toReturnData(JSONObject jsonObject) {
            return ReturnData.builder()
                    .rsltCd(ResultCodeType.of(jsonObject.getString("RSLT_CD")))
                    .rsltMsg(jsonObject.getString("RSLT_MSG"))
                    .ci("CI")
                    .di("DI")
                    .ciUpdate("CI_UPDATE")
                    .build();
        }
    }

}
