package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.InqRsltLstDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CooconInqRsltLstMapper {


    @Insert({
            "INSERT INTO TBLNS_INQRSLT_LST SET ",
             "  LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}",
             ", FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}",
             ", BANK_CD = #{bankCd, jdbcType=VARCHAR}",
             ", BANK_BRCH_CD = #{bankBrchCd, jdbcType=VARCHAR}",
             ", LN_REQ_GBCD = #{lnReqGbcd, jdbcType=VARCHAR}",
             ", LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}",
             ", LN_PRDT_NM = #{lnPrdtNm, jdbcType=VARCHAR}",
             ", LN_RSLT_STCD = #{lnRsltStcd, jdbcType=VARCHAR}",
             ", NRSLT_RSN_CNTN = #{nrsltRsnCntn, jdbcType=VARCHAR}",
             ", LST_LN_LMT_AMT = #{lstLnLmtAmt, jdbcType=VARCHAR}",
             ", LST_LN_RATE = #{lstLnRate, jdbcType=VARCHAR}",
             ", LST_LN_TERM_MM = #{lstLnTermMm, jdbcType=VARCHAR}",
             ", LN_RTN_MTH_CD = #{lnRtnMthCd, jdbcType=VARCHAR}",
             ", LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}",
             ", STRD_RATE = #{strdRate, jdbcType=VARCHAR}",
             ", APPLY_RATE = #{applyRate, jdbcType=VARCHAR}",
             ", PRI_RATE_YN = #{priRateYn, jdbcType=VARCHAR}",
             ", PRI_RATE_RSN_CNTN = #{priRateRsnCntn, jdbcType=VARCHAR}",
             ", PRI_LMT_AMT = #{priLmtAmt, jdbcType=VARCHAR}",
             ", PRI_RATE = #{priRate, jdbcType=VARCHAR}",
             ", LN_VALID_DTTM = #{lnValidDttm, jdbcType=VARCHAR}",
             ", LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}",
             ", LN_MIDRTN_FEE_RATE = #{lnMidrtnFeeRate, jdbcType=VARCHAR}",
             ", LN_MIDRTN_FEE_YN = #{lnMidrtnFeeYn, jdbcType=VARCHAR}",
             ", END_ALLRTN_ABL_YN = #{endAllrtnAblYn, jdbcType=VARCHAR}",
             ", IM_DEPOT_ABL_YN = #{imDepotAblYn, jdbcType=VARCHAR}",
             ", HOLI_DEPOT_ABLE_YN = #{holiDepotAbleYn, jdbcType=VARCHAR}",
             ", PRINITR_EQ_RTN_ABL_YN = #{prinitrEqRtnAblYn, jdbcType=VARCHAR}",
             ", PRIN_EQ_RTN_ABL_YN = #{prinEqRtnAblYn, jdbcType=VARCHAR}",
             ", MINUS_BBOOK_ABL_YN = #{minusBbookAblYn, jdbcType=VARCHAR}",
    })
    void save(InqRsltLstDTO.Create create);

    @Select(
            "SELECT * FROM TBLNS_INQRSLT_LST WHERE LN_REQ_NO = #{lnReqNo}"
    )
    InqRsltLstDTO.Create findByLnReqNo(String lnReqNo);

    @Select(
            "SELECT * FROM TBLNS_INQRSLT_LST" +
            " WHERE " +
            "     LN_REQ_NO = #{lnReqNo}"+
            " AND FINTEC_ORG_MNGNO = #{fintecOrgMngNo, jdbcType=VARCHAR}"+
            " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"
    )
    InqRsltLstDTO.Create findByLnReqNoAndFinEnMnNoAndLoPrdCd(String lnReqNo, String fintecOrgMngNo, String lnPrdtCd);


    @Select(
            "SELECT EXISTS(" +
            "SELECT * FROM TBLNS_INQRSLT_LST" +
            " WHERE " +
            "     LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"+
            " AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
            " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"+
            ")"
    )
    boolean existsByLnReqNoAndFinTecOrgMngNoAndLnPrdtCd(InqRsltLstDTO.Create create);

    @Update({
            "UPDATE TBLNS_INQRSLT_LST SET ",
            "  FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}",
            ", BANK_CD = #{bankCd, jdbcType=VARCHAR}",
            ", BANK_BRCH_CD = #{bankBrchCd, jdbcType=VARCHAR}",
            ", LN_REQ_GBCD = #{lnReqGbcd, jdbcType=VARCHAR}",
            ", LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}",
            ", LN_PRDT_NM = #{lnPrdtNm, jdbcType=VARCHAR}",
            ", LN_RSLT_STCD = #{lnRsltStcd, jdbcType=VARCHAR}",
            ", NRSLT_RSN_CNTN = #{nrsltRsnCntn, jdbcType=VARCHAR}",
            ", LST_LN_LMT_AMT = #{lstLnLmtAmt, jdbcType=VARCHAR}",
            ", LST_LN_RATE = #{lstLnRate, jdbcType=VARCHAR}",
            ", LST_LN_TERM_MM = #{lstLnTermMm, jdbcType=VARCHAR}",
            ", LN_RTN_MTH_CD = #{lnRtnMthCd, jdbcType=VARCHAR}",
            ", LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}",
            ", STRD_RATE = #{strdRate, jdbcType=VARCHAR}",
            ", APPLY_RATE = #{applyRate, jdbcType=VARCHAR}",
            ", PRI_RATE_YN = #{priRateYn, jdbcType=VARCHAR}",
            ", PRI_RATE_RSN_CNTN = #{priRateRsnCntn, jdbcType=VARCHAR}",
            ", PRI_LMT_AMT = #{priLmtAmt, jdbcType=VARCHAR}",
            ", PRI_RATE = #{priRate, jdbcType=VARCHAR}",
            ", LN_VALID_DTTM = #{lnValidDttm, jdbcType=VARCHAR}",
            ", LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}",
            ", LN_MIDRTN_FEE_RATE = #{lnMidrtnFeeRate, jdbcType=VARCHAR}",
            ", LN_MIDRTN_FEE_YN = #{lnMidrtnFeeYn, jdbcType=VARCHAR}",
            ", END_ALLRTN_ABL_YN = #{endAllrtnAblYn, jdbcType=VARCHAR}",
            ", IM_DEPOT_ABL_YN = #{imDepotAblYn, jdbcType=VARCHAR}",
            ", HOLI_DEPOT_ABLE_YN = #{holiDepotAbleYn, jdbcType=VARCHAR}",
            ", PRINITR_EQ_RTN_ABL_YN = #{prinitrEqRtnAblYn, jdbcType=VARCHAR}",
            ", PRIN_EQ_RTN_ABL_YN = #{prinEqRtnAblYn, jdbcType=VARCHAR}",
            ", MINUS_BBOOK_ABL_YN = #{minusBbookAblYn, jdbcType=VARCHAR} ",
            "WHERE ",
            "     LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}",
            " AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}",
            " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"
    })
    void update(InqRsltLstDTO.Create create);

}
