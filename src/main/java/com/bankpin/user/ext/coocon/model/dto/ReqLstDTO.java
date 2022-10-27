package com.bankpin.user.ext.coocon.model.dto;

import com.bankpin.user.ext.coocon.model.dto.CooconDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

public class ReqLstDTO {

    @Data
    @Builder
    public static class Create {

        private String lnReqNo;
        private String fintecOrgMngno;
        private String bankCd;
        private String bankBrchCd;
        private String lnPrdtCd;
        private String brPageUrl;
        private String callbackTelNo;

    }

    @Data
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class RequestParams {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String loReqtNo;
        private String finEnMnNo;
        private String loPrdCd;
        private String cstmCipn;
        private String cstmEml;
        private String cstmNm;
        private String blank1;
        private String fillerI;

    }

}
