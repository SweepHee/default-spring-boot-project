package com.bankpin.user.mrtg.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MrtgevalInfoDTO
{
    @Data
    public static class Create
    {
        private String lnMrtgNo;
        private Integer mrtgSeqNo;
        private String mrtgGbcd;
        private String bldMngNo;
        private String bldNm;
        private String sidoNm;
        private String sigunguNm;
        private String dongNm;
        private BigDecimal earArea;
        private String mainBunji;
        private String subBunji;
        private BigDecimal mktHighAmt;
        private BigDecimal mktAvgAmt;
        private BigDecimal mktLowAmt;
        private BigDecimal apprEvalAmt;
        private String mrtgPosXVal;
        private String mrtgPosYVal;
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

    @Data
    public static class Detail
    {
        private String lnMrtgNo;
        private Integer mrtgSeqNo;
        private String mrtgGbcd;
        private String bldMngNo;
        private String bldNm;
        private String sidoNm;
        private String sigunguNm;
        private String dongNm;
        private BigDecimal earArea;
        private String mainBunji;
        private String subBunji;
        private BigDecimal mktHighAmt;
        private BigDecimal mktAvgAmt;
        private BigDecimal mktLowAmt;
        private BigDecimal apprEvalAmt;
        private String mrtgPosXVal;
        private String mrtgPosYVal;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

    @Data
    public static class Item
    {
        private String lnMrtgNo;
        private Integer mrtgSeqNo;
        private String mrtgGbcd;
        private String bldMngNo;
        private String bldNm;
        private String sidoNm;
        private String sigunguNm;
        private String dongNm;
        private BigDecimal earArea;
        private String mainBunji;
        private String subBunji;
        private BigDecimal mktHighAmt;
        private BigDecimal mktAvgAmt;
        private BigDecimal mktLowAmt;
        private BigDecimal apprEvalAmt;
        private String mrtgPosXVal;
        private String mrtgPosYVal;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

    @Data
    public static class Param
    {
        private String lnMrtgNo;
        private Integer mrtgSeqNo;
        private String mrtgGbcd;
        private String bldMngNo;
        private String bldNm;
        private String sidoNm;
        private String sigunguNm;
        private String dongNm;
        private BigDecimal earArea;
        private String mainBunji;
        private String subBunji;
        private BigDecimal mktHighAmt;
        private BigDecimal mktAvgAmt;
        private BigDecimal mktLowAmt;
        private BigDecimal apprEvalAmt;
        private String mrtgPosXVal;
        private String mrtgPosYVal;
        private LocalDateTime fstEnrDttm;
        private String fstEnrPgm;
        private LocalDateTime lstChgDttm;
        private String lstChgPgm;
    }

}
