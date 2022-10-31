package com.bankpin.user.model.mapper;

import com.bankpin.user.model.dto.MainDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MainMapper
{
    @Select("SELECT" +
            "       INSERT(FN_DECRYPT(TC.CUST_NM), 2, LENGTH(FN_DECRYPT(TC.CUST_NM))-1, '**') AS CUST_NM" +
            "     , TC.CUST_GENDER" +
            "     , INSERT((TIMESTAMPDIFF(year, STR_TO_DATE(IFNULL(TC.CUST_BIRTH, NOW()), '%Y.%m.%d'), NOW()) + 1), 2, 1, 0) AS CUST_BIRTH" +
            "     , TIM.LN_GBCD" +
            "     , TIM.LN_USE_GBCD" +
            "     , TIL.AVL_CNT" +
            "     , TIL.LN_REQ_NO, TIL.FINTEC_ORG_MNGNO, TIL.LST_LN_RATE, TIL.LST_LN_LMT_AMT" +
            "  FROM TBCOM_CUSTMAS TC" +
            " INNER JOIN (" +
            "   SELECT TIM.CUST_CI_NO, TIM.LN_GBCD, TIM.LN_USE_GBCD, TIM.LN_REQ_NO" +
            "     FROM TBLNS_INQ_MAS TIM" +
            "    INNER JOIN (" +
            "       SELECT MAX(LN_REQ_NO) AS LN_REQ_NO" +
            "         FROM TBLNS_INQ_MAS" +
            "        WHERE LN_GBCD = #{lnGbcd, jdbcType=VARCHAR}" +
            "        GROUP BY CUST_CI_NO" +
            "        LIMIT 10" +
            "   ) TTIM ON (TIM.LN_REQ_NO = TTIM.LN_REQ_NO)" +
            " ) TIM ON (TC.CUST_CI_NO = TIM.CUST_CI_NO)" +
            " INNER JOIN (" +
            "   SELECT T.LN_REQ_NO, T.FINTEC_ORG_MNGNO, T.LST_LN_RATE, T.LST_LN_LMT_AMT, T.AVL_CNT" +
            "     FROM (SELECT LN_REQ_NO, FINTEC_ORG_MNGNO, LST_LN_RATE, LST_LN_LMT_AMT" +
            "                , MIN(LST_LN_RATE) OVER(PARTITION BY LN_REQ_NO" +
            "                   ORDER BY LST_LN_RATE ASC, LST_LN_LMT_AMT DESC) as T_RATE" +
            "                , MAX(LST_LN_LMT_AMT) OVER(PARTITION BY LN_REQ_NO" +
            "                   ORDER BY LST_LN_RATE ASC, LST_LN_LMT_AMT DESC) as T_AMT" +
            "                , COUNT(LN_REQ_NO) OVER(PARTITION BY LN_REQ_NO) AS AVL_CNT" +
            "             FROM TBLNS_INQRSLT_LST" +
            "            WHERE LN_RSLT_STCD = #{lnRsltStcd, jdbcType=VARCHAR}) T" +
            "    WHERE T.LST_LN_RATE = T.T_RATE" +
            "      AND T.LST_LN_LMT_AMT = T.T_AMT" +
            " ) TIL ON (TIL.LN_REQ_NO = TIM.LN_REQ_NO)")
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

}
