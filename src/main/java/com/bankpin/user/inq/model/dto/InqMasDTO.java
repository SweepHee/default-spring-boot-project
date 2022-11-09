package com.bankpin.user.inq.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InqMasDTO
{
    @Data
    public static class Create
    {
        private String lnReqNo;
        private LocalDateTime lnInqDttm;
        @Length(max = 14, message = "max = 14")
        private String strLnInqDttm;
        @Length(max = 1, message = "max = 1")
        private String lnGbcd;
        @Length(max = 1, message = "max = 1")
        private String lnReqGbcd;
        @Length(max = 2, message = "max = 2")
        private String lnUseGbcd;
        @Positive
        @Digits(integer = 15, fraction = 0, message = "max = 15")
        private BigDecimal lnReqAmt;
        @Length(max = 3, message = "max = 3")
        private String lnReqTermMm;
        @Length(max = 1, message = "max = 1")
        private String lnRtnMthCd;
        @Length(max = 1, message = "max = 1")
        private String lnRateKindGbcd;
        @Length(max = 3, message = "max = 3")
        private String lnRateCycleCd;
        private Boolean recoverHisYn;
        private Boolean recoverPayCmplYn;
        @Length(max = 24, message = "max = 24")
        private String custRrno;
        @Email
        @Length(max = 40, message = "max = 40")
        private String custEmail;
        @Length(max = 10, message = "max = 10")
        private String custBusiRegno;
        @Length(max = 11, message = "max = 11")
        private String custBusiplcMngno;
        @Length(max = 100, message = "max = 100")
        private String custCmpyNm;
        @Positive
        @Digits(integer = 15, fraction = 0, message = "max = 15")
        private BigDecimal custYearIncomAmt;
        @Length(max = 1, message = "max = 1")
        private String custJobClscd;
        @Length(max = 1, message = "max = 1")
        private String custNhisMemGbcd;
        @Length(max = 1, message = "max = 1")
        private String custBusiUpjongGbcd;
        @Length(max = 1, message = "max = 1")
        private String custEmployGbcd;
        @Length(max = 6, message = "max = 6")
        private String custEnterYyyymm;
        @Length(max = 1, message = "max = 1")
        private String custHousOwnGbcd;
        @Length(max = 1, message = "max = 1")
        private String custHousTypecd;
        @Length(max = 32, message = "max = 32")
        private String custCiNo;
        private LocalDate lnHopeDt;
        @Length(max = 14, message = "max = 14")
        private String strlnHopeDt;
        @Length(max = 1, message = "max = 1")
        private String custCarownYn;
        @Digits(integer = 3, fraction = 0, message = "max = 3")
        private Integer housOwnCnt;
        @Length(max = 1, message = "max = 1")
        private String marriedYn;
        @Positive
        @Digits(integer = 3, fraction = 0, message = "max = 3")
        private Integer childrenCnt;
        @Positive
        @Digits(integer = 15, fraction = 0, message = "max = 15")
        private BigDecimal agoLnAmt;
        @Length(max = 20, message = "max = 20")
        private String lnMrtgNo;
    }

    @Data
    public static class Detail
    {
        private String lnReqNo;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lnInqDttm;
        private String lnGbcd;
        private String lnReqGbcd;
        private String lnUseGbcd;
        private BigDecimal lnReqAmt;
        private String lnReqTermMm;
        private String lnRtnMthCd;
        private String lnRateKindGbcd;
        private String lnRateCycleCd;
        private Boolean recoverHisYn;
        private Boolean recoverPayCmplYn;
        private String custRrno;
        private String custEmail;
        private String custBusiRegno;
        private String custBusiplcMngno;
        private String custCmpyNm;
        private BigDecimal custYearIncomAmt;
        private String custJobClscd;
        private String custNhisMemGbcd;
        private String custBusiUpjongGbcd;
        private String custEmployGbcd;
        private String custEnterYyyymm;
        private String custHousOwnGbcd;
        private String custHousTypecd;
        private String custCiNo;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate lnHopeDt;
        private String custCarownYn;
        private Integer housOwnCnt;
        private String marriedYn;
        private Integer childrenCnt;
        private BigDecimal agoLnAmt;
        private String lnMrtgNo;
    }

    @Data
    public static class Item
    {
        private String lnReqNo;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lnInqDttm;
        private String lnGbcd;
        private String lnReqGbcd;
        private String lnUseGbcd;
        private BigDecimal lnReqAmt;
        private String lnReqTermMm;
        private String lnRtnMthCd;
        private String lnRateKindGbcd;
        private String lnRateCycleCd;
        private Boolean recoverHisYn;
        private Boolean recoverPayCmplYn;
        private BigDecimal custYearIncomAmt;
        private String custJobClscd;
        private String custNhisMemGbcd;
        private String custBusiUpjongGbcd;
        private String custEmployGbcd;
        private String custEnterYyyymm;
        private String custHousOwnGbcd;
        private String custHousTypecd;
        private String custCiNo;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate lnHopeDt;
        private String custCarownYn;
        private Integer housOwnCnt;
        private String marriedYn;
        private Integer childrenCnt;
        private BigDecimal agoLnAmt;
        private String lnMrtgNo;
    }

    @Data
    public static class Param
    {
        @Length(max = 14, message = "max = 14")
        private String lnReqNo;
        @Length(max = 32, message = "max = 32")
        private String custCiNo;
        @Length(max = 14, message = "max = 14")
        private String strLnInqDttm;
        @Length(max = 1, message = "max = 1")
        private String lnGbcd;
        @Length(max = 1, message = "max = 1")
        private String lnReqGbcd;
        @Length(max = 2, message = "max = 2")
        private String lnUseGbcd;
        @Length(max = 20, message = "max = 20")
        private String lnMrtgNo;
    }

}
