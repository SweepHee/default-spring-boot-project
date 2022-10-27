package com.bankpin.user.inq.mapper;

import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InqrsltLstMapper
{
    @Select("<script>" +
            "SELECT" +
            "       LN_REQ_NO" +
            "     , FINTEC_ORG_MNGNO" +
            "     , BANK_CD" +
            "     , BANK_BRCH_CD" +
            "     , LN_REQ_GBCD" +
            "     , LN_PRDT_CD" +
            "     , LN_PRDT_NM" +
            "     , LN_RSLT_STCD" +
            "     , NRSLT_RSN_CNTN" +
            "     , LST_LN_LMT_AMT" +
            "     , LST_LN_RATE" +
            "     , LST_LN_TERM_MM" +
            "     , LN_RTN_MTH_CD" +
            "     , LN_RATE_KIND_GBCD" +
            "     , STRD_RATE" +
            "     , APPLY_RATE" +
            "     , PRI_RATE_YN" +
            "     , PRI_RATE_RSN_CNTN" +
            "     , PRI_LMT_AMT" +
            "     , PRI_RATE" +
            "     , LN_VALID_DTTM" +
            "     , LN_RATE_CYCLE_CD" +
            "     , LN_MIDRTN_FEE_RATE" +
            "     , LN_MIDRTN_FEE_YN" +
            "     , END_ALLRTN_ABL_YN" +
            "     , IM_DEPOT_ABL_YN" +
            "     , HOLI_DEPOT_ABLE_YN" +
            "     , PRINITR_EQ_RTN_ABL_YN" +
            "     , PRIN_EQ_RTN_ABL_YN" +
            "     , MINUS_BBOOK_ABL_YN" +
            "  FROM TBLNS_INQRSLT_LST" +
            " WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "   <choose>" +
            "       <when test='sortOrder == \"rateUp\"'>" +
            " ORDER BY LST_LN_RATE ASC, LST_LN_LMT_AMT DESC" +
            "       </when>" +
            "       <when test='sortOrder == \"rateDown\"'>" +
            " ORDER BY LST_LN_RATE DESC, LST_LN_LMT_AMT DESC" +
            "       </when>" +
            "       <when test='sortOrder == \"amtUp\"'>" +
            " ORDER BY LST_LN_LMT_AMT ASC, LST_LN_RATE ASC" +
            "       </when>" +
            "       <when test='sortOrder == \"amtDown\"'>" +
            " ORDER BY LST_LN_LMT_AMT DESC, LST_LN_RATE ASC" +
            "       </when>" +
            "       <otherwise>" +
            " ORDER BY LST_LN_RATE ASC, LST_LN_LMT_AMT DESC" +
            "       </otherwise>" +
            "   </choose>" +
            "</script>")
    List<InqrsltLstDTO.Item> findAllByLnReqNo(InqrsltLstDTO.Param param);

    @Select("SELECT" +
            "       LN_REQ_NO" +
            "     , FINTEC_ORG_MNGNO" +
            "     , BANK_CD" +
            "     , BANK_BRCH_CD" +
            "     , LN_REQ_GBCD" +
            "     , LN_PRDT_CD" +
            "     , LN_PRDT_NM" +
            "     , LN_RSLT_STCD" +
            "     , NRSLT_RSN_CNTN" +
            "     , LST_LN_LMT_AMT" +
            "     , LST_LN_RATE" +
            "     , LST_LN_TERM_MM" +
            "     , LN_RTN_MTH_CD" +
            "     , LN_RATE_KIND_GBCD" +
            "     , STRD_RATE" +
            "     , APPLY_RATE" +
            "     , PRI_RATE_YN" +
            "     , PRI_RATE_RSN_CNTN" +
            "     , PRI_LMT_AMT" +
            "     , PRI_RATE" +
            "     , LN_VALID_DTTM" +
            "     , LN_RATE_CYCLE_CD" +
            "     , LN_MIDRTN_FEE_RATE" +
            "     , LN_MIDRTN_FEE_YN" +
            "     , END_ALLRTN_ABL_YN" +
            "     , IM_DEPOT_ABL_YN" +
            "     , HOLI_DEPOT_ABLE_YN" +
            "     , PRINITR_EQ_RTN_ABL_YN" +
            "     , PRIN_EQ_RTN_ABL_YN" +
            "     , MINUS_BBOOK_ABL_YN" +
            "  FROM TBLNS_INQRSLT_LST" +
            " WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "   AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}")
    InqrsltLstDTO.Detail findByLnReqNoAndFintecOrgMngno(InqrsltLstDTO.Param param);

    @Insert("INSERT INTO TBLNS_INQRSLT_LST(" +
            "       LN_REQ_NO" +
            "     , FINTEC_ORG_MNGNO" +
            "     , BANK_CD" +
            "     , BANK_BRCH_CD" +
            "     , LN_REQ_GBCD" +
            "     , LN_PRDT_CD" +
            "     , LN_PRDT_NM" +
            "     , LN_RSLT_STCD" +
            "     , NRSLT_RSN_CNTN" +
            "     , LST_LN_LMT_AMT" +
            "     , LST_LN_RATE" +
            "     , LST_LN_TERM_MM" +
            "     , LN_RTN_MTH_CD" +
            "     , LN_RATE_KIND_GBCD" +
            "     , STRD_RATE" +
            "     , APPLY_RATE" +
            "     , PRI_RATE_YN" +
            "     , PRI_RATE_RSN_CNTN" +
            "     , PRI_LMT_AMT" +
            "     , PRI_RATE" +
            "     , LN_VALID_DTTM" +
            "     , LN_RATE_CYCLE_CD" +
            "     , LN_MIDRTN_FEE_RATE" +
            "     , LN_MIDRTN_FEE_YN" +
            "     , END_ALLRTN_ABL_YN" +
            "     , IM_DEPOT_ABL_YN" +
            "     , HOLI_DEPOT_ABLE_YN" +
            "     , PRINITR_EQ_RTN_ABL_YN" +
            "     , PRIN_EQ_RTN_ABL_YN" +
            "     , MINUS_BBOOK_ABL_YN" +
            ") VALUES (" +
            "       #{lnReqNo, jdbcType=VARCHAR}" +
            "     , #{fintecOrgMngno, jdbcType=VARCHAR}" +
            "     , #{bankCd, jdbcType=VARCHAR}" +
            "     , #{bankBrchCd, jdbcType=VARCHAR}" +
            "     , #{lnReqGbcd, jdbcType=VARCHAR}" +
            "     , #{lnPrdtCd, jdbcType=VARCHAR}" +
            "     , #{lnPrdtNm, jdbcType=VARCHAR}" +
            "     , #{lnRsltStcd, jdbcType=VARCHAR}" +
            "     , #{nrsltRsnCntn, jdbcType=VARCHAR}" +
            "     , #{lstLnLmtAmt, jdbcType=DECIMAL}" +
            "     , #{lstLnRate, jdbcType=DECIMAL}" +
            "     , #{lstLnTermMm, jdbcType=VARCHAR}" +
            "     , #{lnRtnMthCd, jdbcType=VARCHAR}" +
            "     , #{lnRateKindGbcd, jdbcType=VARCHAR}" +
            "     , #{strdRate, jdbcType=DECIMAL}" +
            "     , #{applyRate, jdbcType=DECIMAL}" +
            "     , #{priRateYn, jdbcType=VARCHAR}" +
            "     , #{priRateRsnCntn, jdbcType=VARCHAR}" +
            "     , #{priLmtAmt, jdbcType=DECIMAL}" +
            "     , #{priRate, jdbcType=DECIMAL}" +
            "     , #{lnValidDttm, jdbcType=VARCHAR}" +
            "     , #{lnRateCycleCd, jdbcType=VARCHAR}" +
            "     , #{lnMidrtnFeeRate, jdbcType=DECIMAL}" +
            "     , #{lnMidrtnFeeYn, jdbcType=TINYINT}" +
            "     , #{endAllrtnAblYn, jdbcType=TINYINT}" +
            "     , #{imDepotAblYn, jdbcType=TINYINT}" +
            "     , #{holiDepotAbleYn, jdbcType=TINYINT}" +
            "     , #{prinitrEqRtnAblYn, jdbcType=TINYINT}" +
            "     , #{prinEqRtnAblYn, jdbcType=TINYINT}" +
            "     , #{minusBbookAblYn, jdbcType=TINYINT}" +
            ")")
    int save(InqrsltLstDTO.Create create);

    @Update("<script>" +
            "UPDATE TBLNS_INQRSLT_LST" +
            "   SET" +
            "   <if test='bankCd != null and bankCd != \"\"'>" +
            "       BANK_CD = #{bankCd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='bankBrchCd != null and bankBrchCd != \"\"'>" +
            "     , BANK_BRCH_CD = #{bankBrchCd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnReqGbcd != null and lnReqGbcd != \"\"'>" +
            "     , LN_REQ_GBCD = #{lnReqGbcd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnPrdtCd != null and lnPrdtCd != \"\"'>" +
            "     , LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnPrdtNm != null and lnPrdtNm != \"\"'>" +
            "     , LN_PRDT_NM = #{lnPrdtNm, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnRsltStcd != null and lnRsltStcd != \"\"'>" +
            "     , LN_RSLT_STCD = #{lnRsltStcd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='nrsltRsnCntn != null and nrsltRsnCntn != \"\"'>" +
            "     , NRSLT_RSN_CNTN = #{nrsltRsnCntn, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lstLnLmtAmt != null and lstLnLmtAmt != \"\"'>" +
            "     , LST_LN_LMT_AMT = #{lstLnLmtAmt, jdbcType=DECIMAL}" +
            "   </if>" +
            "   <if test='lstLnRate != null and lstLnRate != \"\"'>" +
            "     , LST_LN_RATE = #{lstLnRate, jdbcType=DECIMAL}" +
            "   </if>" +
            "   <if test='lstLnTermMm != null and lstLnTermMm != \"\"'>" +
            "     , LST_LN_TERM_MM = #{lstLnTermMm, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnRtnMthCd != null and lnRtnMthCd != \"\"'>" +
            "     , LN_RTN_MTH_CD = #{lnRtnMthCd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnRateKindGbcd != null and lnRateKindGbcd != \"\"'>" +
            "     , LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='strdRate != null and strdRate != \"\"'>" +
            "     , STRD_RATE = #{strdRate, jdbcType=DECIMAL}" +
            "   </if>" +
            "   <if test='applyRate != null and applyRate != \"\"'>" +
            "     , APPLY_RATE = #{applyRate, jdbcType=DECIMAL}" +
            "   </if>" +
            "   <if test='priRateYn != null and priRateYn != \"\"'>" +
            "     , PRI_RATE_YN = #{priRateYn, jdbcType=TINYINT}" +
            "   </if>" +
            "   <if test='priRateRsnCntn != null and priRateRsnCntn != \"\"'>" +
            "     , PRI_RATE_RSN_CNTN = #{priRateRsnCntn, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='priLmtAmt != null and priLmtAmt != \"\"'>" +
            "     , PRI_LMT_AMT = #{priLmtAmt, jdbcType=DECIMAL}" +
            "   </if>" +
            "   <if test='priRate != null and priRate != \"\"'>" +
            "     , PRI_RATE = #{priRate, jdbcType=DECIMAL}" +
            "   </if>" +
            "   <if test='lnValidDttm != null and lnValidDttm != \"\"'>" +
            "     , LN_VALID_DTTM = #{lnValidDttm, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnRateCycleCd != null and lnRateCycleCd != \"\"'>" +
            "     , LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}" +
            "   </if>" +
            "   <if test='lnMidrtnFeeRate != null and lnMidrtnFeeRate != \"\"'>" +
            "     , LN_MIDRTN_FEE_RATE = #{lnMidrtnFeeRate, jdbcType=DECIMAL}" +
            "   </if>" +
            "   <if test='lnMidrtnFeeYn != null and lnMidrtnFeeYn != \"\"'>" +
            "     , LN_MIDRTN_FEE_YN = #{lnMidrtnFeeYn, jdbcType=TINYINT}" +
            "   </if>" +
            "   <if test='endAllrtnAblYn != null and endAllrtnAblYn != \"\"'>" +
            "     , END_ALLRTN_ABL_YN = #{endAllrtnAblYn, jdbcType=TINYINT}" +
            "   </if>" +
            "   <if test='imDepotAblYn != null and imDepotAblYn != \"\"'>" +
            "     , IM_DEPOT_ABL_YN = #{imDepotAblYn, jdbcType=TINYINT}" +
            "   </if>" +
            "   <if test='holiDepotAbleYn != null and holiDepotAbleYn != \"\"'>" +
            "     , HOLI_DEPOT_ABLE_YN = #{holiDepotAbleYn, jdbcType=TINYINT}" +
            "   </if>" +
            "   <if test='prinitrEqRtnAblYn != null and prinitrEqRtnAblYn != \"\"'>" +
            "     , PRINITR_EQ_RTN_ABL_YN = #{prinitrEqRtnAblYn, jdbcType=TINYINT}" +
            "   </if>" +
            "   <if test='prinEqRtnAblYn != null and prinEqRtnAblYn != \"\"'>" +
            "     , PRIN_EQ_RTN_ABL_YN = #{prinEqRtnAblYn, jdbcType=TINYINT}" +
            "   </if>" +
            "     , MINUS_BBOOK_ABL_YN = #{minusBbookAblYn, jdbcType=TINYINT}" +
            " WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "   AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}" +
            "</script>")
    int edit(InqrsltLstDTO.Create create);

    @Delete("<script>" +
            "DELETE FROM TBLNS_INQRSLT_LST" +
            " WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "   <if test='fintecOrgMngno != null and fintecOrgMngno != \"\"'>" +
            "   AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}" +
            "   </if>" +
            "</script>")
    int remove(InqrsltLstDTO.Param param);

}
