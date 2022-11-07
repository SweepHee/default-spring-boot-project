package com.bankpin.user.mapper;

import com.bankpin.user.model.dto.MainDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MainMapper
{
    @Select("SELECT" +
            "	    INSERT(FN_DECRYPT(TC.CUST_NM), 2, LENGTH(FN_DECRYPT(TC.CUST_NM))-1,	'**') AS CUST_NM" +
            "     , TC.CUST_GENDER" +
            "     , INSERT((TIMESTAMPDIFF(year, STR_TO_DATE(" +
            "               IFNULL(NULLIF(TC.CUST_BIRTH, ''), DATE_FORMAT(NOW(), '%Y.%m.%d')), '%Y.%m.%d'), NOW()) + 1)," +
            "               2, 1, 0) AS CUST_BIRTH" +
            "     , TIM.LN_GBCD" +
            "     , TIM.LN_USE_GBCD" +
            "     , TIM.CUST_CI_NO" +
            "     , TIL.AVL_CNT" +
            "     , TIL.LN_REQ_NO" +
            "     , TIL.LST_LN_RATE" +
            "     , TIL.LST_LN_LMT_AMT" +
            "  FROM TBCOM_CUSTMAS TC" +
            " INNER JOIN(" +
            "       SELECT" +
            "              STIM.CUST_CI_NO" +
            "            , STIM.LN_GBCD" +
            "            , STIM.LN_USE_GBCD" +
            "            , STIM.LN_REQ_NO" +
            "         FROM TBLNS_INQ_MAS STIM" +
            "        INNER JOIN (" +
            "              SELECT MAX(LN_REQ_NO) AS LN_REQ_NO" +
            "                FROM TBLNS_INQ_MAS" +
            "               WHERE LN_GBCD = #{lnGbcd, jdbcType=VARCHAR}" +
            "               GROUP BY CUST_CI_NO LIMIT 10" +
            "            ) TTIM ON (STIM.LN_REQ_NO = TTIM.LN_REQ_NO)" +
            "     ) TIM ON (TC.CUST_CI_NO = TIM.CUST_CI_NO)" +
            " INNER JOIN (" +
            "       SELECT" +
            "              LN_REQ_NO, COUNT(LN_REQ_NO) AS AVL_CNT" +
            "            , MIN(LST_LN_RATE) AS LST_LN_RATE" +
            "            , MAX(LST_LN_LMT_AMT) AS LST_LN_LMT_AMT" +
            "	      FROM TBLNS_INQRSLT_LST" +
            "        WHERE LN_RSLT_STCD = #{lnRsltStcd, jdbcType=VARCHAR}" +
            "        GROUP BY LN_REQ_NO" +
            "    ) TIL ON (TIL.LN_REQ_NO = TIM.LN_REQ_NO)")
    List<MainDTO.FastItem> findByLnReqGbcd(MainDTO.Param param);


    @Select("SELECT" +
            "       TIL.LN_REQ_NO" +
            "     , TIL.FINTEC_ORG_MNGNO" +
            "     , TIL.BANK_CD" +
            "     , TIL.BANK_BRCH_CD" +
            "     , TIL.LN_REQ_GBCD" +
            "     , TIL.LN_PRDT_CD" +
            "     , TIL.LN_PRDT_NM" +
            "     , TIL.LST_LN_LMT_AMT" +
            "     , TIL.LST_LN_RATE" +
            "  FROM TBLNS_INQ_MAS TIM" +
            " INNER JOIN TBLNS_INQRSLT_LST TIL ON (" +
            "           TIM.LN_REQ_NO = TIL.LN_REQ_NO" +
            "       AND TIL.LN_RSLT_STCD = #{lnRsltStcd, jdbcType=VARCHAR})" +
            " WHERE TIM.LN_GBCD = #{lnGbcd, jdbcType=VARCHAR}" +
            "   AND TIM.CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}" +
            " ORDER BY TIL.LN_REQ_NO ASC")
    List<MainDTO.LoanItem> findByCustCiNoAndLnRsltStcd(MainDTO.LoanParam param);

    @Select("SELECT" +
            "       MIN(B.MIN_LST_LN_RATE) AS MIN_LST_LN_RATE" +
            "     , MAX(B.MAX_LST_LN_RATE) AS MAX_LST_LN_RATE" +
            "     , SUM((B.MIN_LST_LN_RATE + (B.MAX_LST_LN_RATE - B.MIN_LST_LN_RATE) / 2)) / COUNT(*) AS CALC_LST_LN_RATE" +
            "  FROM (SELECT MIN(TIM.LST_LN_RATE) AS MIN_LST_LN_RATE" +
            "             , MAX(TIM.LST_LN_RATE) AS MAX_LST_LN_RATE" +
            "          FROM (SELECT S_TIL.*" +
            "                  FROM TBLNS_INQ_MAS S_TIM" +
            "                 INNER JOIN TBLNS_INQRSLT_LST S_TIL ON (S_TIM.LN_REQ_NO = S_TIL.LN_REQ_NO)" +
            "         WHERE S_TIM.LN_GBCD = #{lnGbcd, jdbcType=INTEGER}) TIM" +
            "     ) B")
    MainDTO.RateSummary findRageSummary(MainDTO.Param param);

    @Select("SELECT" +
            "       TIL.LN_REQ_NO" +
            " 	  , TIL.FINTEC_ORG_MNGNO" +
            " 	  , TIL.BANK_CD" +
            " 	  , TIL.LST_LN_RATE" +
            " 	  , TRUNCATE(TIL.LST_LN_LMT_AMT / 10000, -1) AS LST_LN_LMT_AMT" +
            "     , TIL.LN_PRDT_CD" +
            "     , TIL.LN_PRDT_NM" +
            "  FROM (SELECT S_TIL.*" +
            " 		   FROM TBLNS_INQ_MAS S_TIM" +
            " 		  INNER JOIN TBLNS_INQRSLT_LST S_TIL ON (S_TIM.LN_REQ_NO = S_TIL.LN_REQ_NO)" +
            "  		  WHERE S_TIM.LN_GBCD = #{lnGbcd, jdbcType=INTEGER}" +
            "     ) TIL" +
            " ORDER BY TIL.LST_LN_RATE ASC, TIL.LST_LN_LMT_AMT DESC")
    List<MainDTO.RateInfo> findAllGroupByBankCd(MainDTO.Param param);

}
