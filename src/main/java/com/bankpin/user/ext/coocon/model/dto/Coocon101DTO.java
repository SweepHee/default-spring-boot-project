package com.bankpin.user.ext.coocon.model.dto;

import com.bankpin.user.terms.model.dto.TermsAgreeDTO;
import com.bankpin.user.terms.model.type.TermsType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Coocon101DTO {

    @Data
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Request {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String svcDs;
        private String cstmNm;
        private String rRNo;
        private String cI;
        private String brNo;
        private String wpDsCd;
        private String wpNm;
        private String sCrtcMthd;
        private String sCrtcDtTm;
        private String cstmClPN;
        private String cpCrtcTlcm;
        private String cdCrtcNo;
        private String crtcIsDt;
        private String crtcIsInfo;
        private String email;
        private String sCrtcUNo;
        private String encCI;
        private String blank1;
        private int listCnt1;

        @JsonProperty("INLIST1")
        private List<InList1> inList1 = new ArrayList<>();
        private String jonCls;
        private String mbrDs;
        private String bsTp;
        private String eplymtTpbs;
        private String anIncm;
        private String jnDt;
        private String hsOwTpbs;
        private String hsTp;
        private String loReqAmt;
        private String loTeM;
        private String rpayMthdDsCd;
        private String rtTpbsDsCd;
        private String chgCycl;
        private String oInstLoRpayAmt;
        private String oInstLoRpayCnt;
        private String prsnRehabDs;
        private String rehabPymtCmplYN;
        private String blank2;
        private int listCnt2;

        @JsonProperty("INLIST2")
        private List<InList2> inList2 = new ArrayList<>();
        private String scrpInfoYN;
        private String nHISQYNCIsNo;
        private String nHISQYNCIsYMD;
        private String nHISQYNCCWNm;
        private String nHISQYNCCWMDCd;
        private String nHISQYNCCWQGYMD;
        private String nHISQYNCCWQLYMD;
        private String blank3;
        private String nHISPCIqSYM;
        private String nHISPCIqEYM;
        private String nHISPCBrNoTY;
        private String nHISPCWPNmTY;
        private String nHISPCPyrNoTY;
        private String blank4;
        private int listCnt3;

        @JsonProperty("INLIST3")
        private List<InList3> inList3 = new ArrayList<>();
        private String gr1;
        private String sc1;
        private String gr2;
        private String sc2;
        private String carNo;
        private String cstmDs;
        private String dtlJonCls;
        private String kBCd;
        private String kBPyCd;
        private String fillerI;
        private String inList1str;
        private String inList2str;
        private String inList3str;

        @Data
        @Builder
        @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
        public static class InList1 {
            private String agrmtItCd;
            private String agrmtDT;
            private String agrmtYN;

            public static InList1 termsAgreeToInList1(TermsAgreeDTO.Create agree) {
                return InList1.builder()
                        .agrmtItCd(TermsType.of(agree.getTermsType()).getApiCode())
                        .agrmtDT(agree.getUpdatedDate())
                        .agrmtYN(agree.getUpdatedDate())
                        .build();
            }
        }

        @Data
        @Builder
        @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
        public static class InList2 {
            private String loReqtNo;
            private String loPrdCd;

        }

        @Data
        @Builder
        @JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
        public static class InList3 {
            private String NHISPCPYMM0;
            private String NHISPCIsNoTY;
            private String NHISPCDtTY;
            private String NHISPCHINAmtM0;
            private String NHISPCHIPAmtM0;
            private String NHISPCLCNAmtM0;
            private String NHISPCLCPAmtM0;
        }

    }


    @Data
    public static class Response {

        @JsonProperty("COMMON")
        private CooconDTO.Common Common;
        private String fillerI;

    }

}
