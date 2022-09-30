package com.bankpin.user.mrtg.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MrtgMasDTO
{
    @Data
    public static class Create
    {
        private String lnMrtgNo;
        private String mrtgKindCd;
        private BigDecimal earAreaSum;
        private BigDecimal mktHighSumAmt;
        private BigDecimal mktAvgSumAmt;
        private BigDecimal mktLowSumAmt;
        private BigDecimal apprEvalSumAmt;
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

    @Data
    public static class Detail
    {
        private String lnMrtgNo;
        private String mrtgKindCd;
        private BigDecimal earAreaSum;
        private BigDecimal mktHighSumAmt;
        private BigDecimal mktAvgSumAmt;
        private BigDecimal mktLowSumAmt;
        private BigDecimal apprEvalSumAmt;
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

    @Data
    public static class Item
    {
        private String lnMrtgNo;
        private String mrtgKindCd;
        private BigDecimal earAreaSum;
        private BigDecimal mktHighSumAmt;
        private BigDecimal mktAvgSumAmt;
        private BigDecimal mktLowSumAmt;
        private BigDecimal apprEvalSumAmt;
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

    @Data
    public static class Param
    {
        private String lnMrtgNo;
        private String mrtgKindCd;
        private BigDecimal earAreaSum;
        private BigDecimal mktHighSumAmt;
        private BigDecimal mktAvgSumAmt;
        private BigDecimal mktLowSumAmt;
        private BigDecimal apprEvalSumAmt;
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

}
