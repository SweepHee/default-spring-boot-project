package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.InqMasDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CooconInqMasMapper {

    @Select("SELECT EXISTS(SELECT * FROM TBLNS_INQ_MAS WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR})")
    Boolean existsByLnReqNo(String lnReqNo);

    @Insert(
            "INSERT INTO TBLNS_INQ_MAS SET "+
             "  LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"+
             ", LN_INQ_DTTM = #{lnInqDttm, jdbcType=VARCHAR}"+
             ", LN_GBCD = #{lnGbcd, jdbcType=VARCHAR}"+
             ", LN_REQ_GBCD = #{lnReqGbcd, jdbcType=VARCHAR}"+
             ", LN_USE_GBCD = #{lnUseGbcd, jdbcType=VARCHAR}"+
             ", LN_REQ_AMT = #{lnReqAmt, jdbcType=VARCHAR}"+
             ", LN_REQ_TERM_MM = #{lnReqTermMm, jdbcType=VARCHAR}"+
             ", LN_RTN_MTH_CD = #{lnRtnMthCd, jdbcType=VARCHAR}"+
             ", LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}"+
             ", LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}"+
             ", RECOVER_HIS_YN = #{recoverHisYn, jdbcType=VARCHAR}"+
             ", RECOVER_PAY_CMPL_YN = #{recoverPayCmplYn, jdbcType=VARCHAR}"+
             ", CUST_RRNO = FN_ENCRYPT(#{custRrno, jdbcType=VARCHAR})"+
             ", CUST_EMAIL = FN_ENCRYPT(#{custEmail, jdbcType=VARCHAR})"+
             ", CUST_BUSI_REGNO = FN_ENCRYPT(#{custBusiRegno, jdbcType=VARCHAR})"+
             ", CUST_BUSIPLC_MNGNO = FN_ENCRYPT(#{custBusiplcMngno, jdbcType=VARCHAR})"+
             ", CUST_CMPY_NM = FN_ENCRYPT(#{custCmpyNm, jdbcType=VARCHAR})"+
             ", CUST_YEAR_INCOM_AMT = #{custYearIncomAmt, jdbcType=VARCHAR}"+
             ", CUST_JOB_CLSCD = #{custJobClscd, jdbcType=VARCHAR}"+
             ", CUST_NHIS_MEM_GBCD = #{custNhisMemGbcd, jdbcType=VARCHAR}"+
             ", CUST_BUSI_UPJONG_GBCD = #{custBusiUpjongGbcd, jdbcType=VARCHAR}"+
             ", CUST_EMPLOY_GBCD = #{custEmployGbcd, jdbcType=VARCHAR}"+
             ", CUST_ENTER_YYYYMM = #{custEnterYyyymm, jdbcType=VARCHAR}"+
             ", CUST_HOUS_OWN_GBCD = #{custHousOwnGbcd, jdbcType=VARCHAR}"+
             ", CUST_HOUS_TYPECD = #{custHousTypecd, jdbcType=VARCHAR}"+
             ", CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"+
             ", LN_HOPE_DT = #{lnHopeDt, jdbcType=VARCHAR}"+
             ", CUST_CAROWN_YN = #{custCarownYn, jdbcType=VARCHAR}"+
             ", HOUS_OWN_CNT = #{housOwnCnt, jdbcType=VARCHAR}"+
             ", MARRIED_YN = #{marriedYn, jdbcType=VARCHAR}"+
             ", CHILDREN_CNT = #{childrenCnt, jdbcType=VARCHAR}"+
             ", AGO_LN_AMT = #{agoLnAmt, jdbcType=VARCHAR}"+
             ", LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}"
    )
    void save(InqMasDTO.Create create);

    @Update(
            "UPDATE TBLNS_INQ_MAS SET"+
            "  LN_INQ_DTTM = #{lnInqDttm, jdbcType=VARCHAR}"+
            ", LN_GBCD = #{lnGbcd, jdbcType=VARCHAR}"+
            ", LN_REQ_GBCD = #{lnReqGbcd, jdbcType=VARCHAR}"+
            ", LN_USE_GBCD = #{lnUseGbcd, jdbcType=VARCHAR}"+
            ", LN_REQ_AMT = #{lnReqAmt, jdbcType=VARCHAR}"+
            ", LN_REQ_TERM_MM = #{lnReqTermMm, jdbcType=VARCHAR}"+
            ", LN_RTN_MTH_CD = #{lnRtnMthCd, jdbcType=VARCHAR}"+
            ", LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}"+
            ", LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}"+
            ", RECOVER_HIS_YN = #{recoverHisYn, jdbcType=VARCHAR}"+
            ", RECOVER_PAY_CMPL_YN = #{recoverPayCmplYn, jdbcType=VARCHAR}"+
            ", CUST_RRNO = #{custRrno, jdbcType=VARCHAR}"+
            ", CUST_EMAIL = #{custEmail, jdbcType=VARCHAR}"+
            ", CUST_BUSI_REGNO = #{custBusiRegno, jdbcType=VARCHAR}"+
            ", CUST_BUSIPLC_MNGNO = #{custBusiplcMngno, jdbcType=VARCHAR}"+
            ", CUST_CMPY_NM = #{custCmpyNm, jdbcType=VARCHAR}"+
            ", CUST_YEAR_INCOM_AMT = #{custYearIncomAmt, jdbcType=VARCHAR}"+
            ", CUST_JOB_CLSCD = #{custJobClscd, jdbcType=VARCHAR}"+
            ", CUST_NHIS_MEM_GBCD = #{custNhisMemGbcd, jdbcType=VARCHAR}"+
            ", CUST_BUSI_UPJONG_GBCD = #{custBusiUpjongGbcd, jdbcType=VARCHAR}"+
            ", CUST_EMPLOY_GBCD = #{custEmployGbcd, jdbcType=VARCHAR}"+
            ", CUST_ENTER_YYYYMM = #{custEnterYyyymm, jdbcType=VARCHAR}"+
            ", CUST_HOUS_OWN_GBCD = #{custHousOwnGbcd, jdbcType=VARCHAR}"+
            ", CUST_HOUS_TYPECD = #{custHousTypecd, jdbcType=VARCHAR}"+
            ", CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"+
            ", LN_HOPE_DT = #{lnHopeDt, jdbcType=VARCHAR}"+
            ", CUST_CAROWN_YN = #{custCarownYn, jdbcType=VARCHAR}"+
            ", HOUS_OWN_CNT = #{housOwnCnt, jdbcType=VARCHAR}"+
            ", MARRIED_YN = #{marriedYn, jdbcType=VARCHAR}"+
            ", CHILDREN_CNT = #{childrenCnt, jdbcType=VARCHAR}"+
            ", AGO_LN_AMT = #{agoLnAmt, jdbcType=VARCHAR}"+
            ", LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR} "+
            "WHERE" +
            "  LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"
    )
    void update(InqMasDTO.Create create);

}
