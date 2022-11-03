package com.bankpin.user.ext.kcb.model.dto;


import com.bankpin.user.ext.kcb.model.type.ResultCodeType;
import kcb.org.json.JSONObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PassAppDTO {

    @Data
    @Builder
    public static class Param {

        private String cpCd;

        @NotNull
        @Pattern(regexp = "^(01|02|03|04|05|06)$", message = "통신사코드에 유효하지 않은 문자열이 있습니다.")
        private String telComCd;

        private String userIp;
        private String siteUrl;
        private String siteName;

        @NotNull
        @Pattern(regexp = "^(00|01|02|03|04|99|EC)$", message = "인증요청사유코드에 유효하지 않은 문자열이 있습니다.")
        private String rqstCausCd;

        @NotNull
        @Pattern(regexp = "^[Y]", message = "동의여부1에 동의하지 않았습니다.")
        private String agree1;

        @NotNull
        @Pattern(regexp = "^[Y]", message = "동의여부2에 동의하지 않았습니다.")
        private String agree2;

        @NotNull
        @Pattern(regexp = "^[Y]", message = "동의여부3에 동의하지 않았습니다.")
        private String agree3;

        @NotNull
        @Pattern(regexp = "^[Y]", message = "동의여부4에 동의하지 않았습니다.")
        private String agree4;

    }

    @Data
    @Builder
    public static class ReturnData {

        private ResultCodeType rsltCd;
        private String rsltMsg;
        private String cpCd;
        private String txSeqNo;
        private String appToken;
        private String tid;

        private DeviceType deviceType;

        @Data
        @Builder
        private static class DeviceType {

            private String sktAndroid;
            private String sktIos;
            private String ktAndroid;
            private String ktIos;
            private String lguAndroid;
            private String lguIos;

        }


        public static ReturnData toReturnData(JSONObject jsonObject) {

            String appToken = jsonObject.getString("APP_TOKEN");
            String tid = jsonObject.getString("TID");

            DeviceType deviceType = DeviceType.builder()
                    .sktAndroid("intent://sktauth?agentTID="+tid+"&appToken="+appToken+"#Intent;" +
                            "scheme=tauthlink;" +
                            "action=android.intent.action.VIEW;" +
                            "category=android.intent.category.BROWSABLE;package=com.sktelecom.tauth;" +
                            "end")
                    .sktIos("https://www.sktpass.com/applink/sktauth?agentTID="+tid+"&appToken="+appToken+"&callBackUrl=")
                    .ktAndroid("intent://requestktauth?appToken="+appToken+"#Intent;" +
                            "scheme=ktauthexternalcall;" +
                            "action=android.intent.action.VIEW;" +
                            "category=android.intent.category.BROWSABLE;package=com.kt..ktauth;end")
                    .ktIos("https://fido.kt.com/ktauthPass?appToken="+appToken+"&callBackUrl=")
                    .lguAndroid("intent://?SID="+tid+"&appToken="+appToken+"&sc=001#Intent;" +
                            "scheme=upluscorporation;" +
                            "package=com.lguplus.smartotp;" +
                            "end")
                    .lguIos("https://fido.uplus.co.kr/rp/lgauthPass?SID="+tid+"&appToken="+appToken+"&sc=001&callBackUrl=")
                    .build();


            return ReturnData.builder()
                    .rsltCd(ResultCodeType.of(jsonObject.getString("RSLT_CD")))
                    .rsltMsg(jsonObject.getString("RSLT_MSG"))
                    .cpCd(jsonObject.getString("CP_CD"))
                    .txSeqNo(jsonObject.getString("TX_SEQ_NO"))
                    .appToken(jsonObject.getString("APP_TOKEN"))
                    .tid(jsonObject.getString("TID"))
                    .deviceType(deviceType)
                    .build();

        }

    }

}
