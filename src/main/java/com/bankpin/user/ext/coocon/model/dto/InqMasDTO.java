package com.bankpin.user.ext.coocon.model.dto;

import lombok.Builder;
import lombok.Data;

public class InqMasDTO {

    @Builder
    @Data
    public static class Create {

        private String lnReqNo;
        private String lnInqDttm;
        private String lnGbcd;
        private String lnReqGbcd;
        private String lnUseGbcd;
        private String lnReqAmt;
        private String lnReqTermMm;
        private String lnRtnMthCd;
        private String lnRateKindGbcd;
        private String lnRateCycleCd;
        private String recoverHisYn;
        private String recoverPayCmplYn;
        private String custRrno;
        private String custEmail;
        private String custBusiRegno;
        private String custBusiplcMngno;
        private String custCmpyNm;
        private String custYearIncomAmt;
        private String custJobClscd;
        private String custNhisMemGbcd;
        private String custBusiUpjongGbcd;
        private String custEmployGbcd;
        private String custEnterYyyymm;
        private String custHousOwnGbcd;
        private String custHousTypecd;
        private String custCiNo;
        private String lnHopeDt;
        private String custCarownYn;
        private String housOwnCnt;
        private String marriedYn;
        private String childrenCnt;
        private String agoLnAmt;
        private String lnMrtgNo;

    }


}
