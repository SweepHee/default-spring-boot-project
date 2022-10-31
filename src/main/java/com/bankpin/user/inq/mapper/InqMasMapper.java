package com.bankpin.user.inq.mapper;

import com.bankpin.user.inq.model.dto.InqMasDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InqMasMapper
{
    @Select("<script>" +
            "SELECT" +
            "       LN_REQ_NO, LN_INQ_DTTM, LN_GBCD, LN_REQ_GBCD" +
            "     , LN_USE_GBCD, LN_REQ_AMT, LN_REQ_TERM_MM, LN_RTN_MTH_CD" +
            "     , LN_RATE_KIND_GBCD, LN_RATE_CYCLE_CD, RECOVER_HIS_YN" +
            "     , RECOVER_PAY_CMPL_YN" +
            "     , FN_DECRYPT(CUST_RRNO) AS CUST_RRNO" +
            "     , FN_DECRYPT(CUST_EMAIL) AS CUST_EMAIL" +
            "     , FN_DECRYPT(CUST_BUSI_REGNO) AS CUST_BUSI_REGNO" +
            "     , FN_DECRYPT(CUST_BUSIPLC_MNGNO) AS CUST_BUSIPLC_MNGNO" +
            "     , FN_DECRYPT(CUST_CMPY_NM) AS CUST_CMPY_NM" +
            "     , CUST_YEAR_INCOM_AMT, CUST_JOB_CLSCD, CUST_NHIS_MEM_GBCD" +
            "     , CUST_BUSI_UPJONG_GBCD, CUST_EMPLOY_GBCD, CUST_ENTER_YYYYMM" +
            "     , CUST_HOUS_OWN_GBCD, CUST_HOUS_TYPECD, CUST_CI_NO" +
            "     , LN_HOPE_DT, CUST_CAROWN_YN, HOUS_OWN_CNT, MARRIED_YN" +
            "     , CHILDREN_CNT, AGO_LN_AMT, LN_MRTG_NO" +
            "  FROM TBLNS_INQ_MAS" +
            " WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "<if test='custCiNo != null'>" +
            "   AND CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}" +
            "</if>" +
            "</script>")
    InqMasDTO.Detail findByCustCiNo(InqMasDTO.Param param);

    @Select("SELECT" +
            "       LN_REQ_NO, LN_INQ_DTTM, LN_GBCD, LN_REQ_GBCD" +
            "     , LN_USE_GBCD, LN_REQ_AMT, LN_REQ_TERM_MM, LN_RTN_MTH_CD" +
            "     , LN_RATE_KIND_GBCD, LN_RATE_CYCLE_CD, RECOVER_HIS_YN" +
            "     , RECOVER_PAY_CMPL_YN" +
            "     , FN_DECRYPT(CUST_CMPY_NM) AS CUST_CMPY_NM" +
            "     , CUST_YEAR_INCOM_AMT, CUST_JOB_CLSCD, CUST_NHIS_MEM_GBCD" +
            "     , CUST_BUSI_UPJONG_GBCD, CUST_EMPLOY_GBCD, CUST_ENTER_YYYYMM" +
            "     , CUST_HOUS_OWN_GBCD, CUST_HOUS_TYPECD, CUST_CI_NO" +
            "     , LN_HOPE_DT, CUST_CAROWN_YN, HOUS_OWN_CNT, MARRIED_YN" +
            "     , CHILDREN_CNT, AGO_LN_AMT, LN_MRTG_NO" +
            "  FROM TBLNS_INQ_MAS" +
            " WHERE LN_GBCD = #{lnGbcd, jdbcType=VARCHAR}" +
            " ORDER BY LN_INQ_DTTM DESC")
    List<InqMasDTO.Item> findAll(InqMasDTO.Param param);

    @Select("SELECT" +
            "       LPAD(MAX(CAST(LN_REQ_NO AS UNSIGNED))+1,14,'0') AS LN_REQ_NO" +
            "  FROM TBLNS_INQ_MAS")
    String findByMaxLnReqNo();

    @Insert("INSERT INTO TBLNS_INQ_MAS (" +
            "       LN_REQ_NO, LN_INQ_DTTM, LN_GBCD, LN_REQ_GBCD" +
            "     , LN_USE_GBCD, LN_REQ_AMT, LN_REQ_TERM_MM, LN_RTN_MTH_CD" +
            "     , LN_RATE_KIND_GBCD, LN_RATE_CYCLE_CD, RECOVER_HIS_YN" +
            "     , RECOVER_PAY_CMPL_YN" +
            "     , CUST_RRNO" +
            "     , CUST_EMAIL" +
            "     , CUST_BUSI_REGNO" +
            "     , CUST_BUSIPLC_MNGNO" +
            "     , CUST_CMPY_NM" +
            "     , CUST_YEAR_INCOM_AMT, CUST_JOB_CLSCD, CUST_NHIS_MEM_GBCD" +
            "     , CUST_BUSI_UPJONG_GBCD, CUST_EMPLOY_GBCD, CUST_ENTER_YYYYMM" +
            "     , CUST_HOUS_OWN_GBCD, CUST_HOUS_TYPECD, CUST_CI_NO" +
            "     , LN_HOPE_DT, CUST_CAROWN_YN, HOUS_OWN_CNT, MARRIED_YN" +
            "     , CHILDREN_CNT, AGO_LN_AMT, LN_MRTG_NO" +
            ") VALUES (" +
            "       #{lnReqNo, jdbcType=VARCHAR}, #{lnInqDttm, jdbcType=TIMESTAMP}, #{lnGbcd, jdbcType=VARCHAR}, #{lnReqGbcd, jdbcType=VARCHAR}" +
            "     , #{lnUseGbcd, jdbcType=VARCHAR}, #{lnReqAmt, jdbcType=DECIMAL}, #{lnReqTermMm, jdbcType=VARCHAR}, #{lnRtnMthCd, jdbcType=VARCHAR}" +
            "     , #{lnRateKindGbcd, jdbcType=VARCHAR}, #{lnRateCycleCd, jdbcType=VARCHAR}, #{recoverHisYn, jdbcType=TINYINT}" +
            "     , #{recoverPayCmplYn, jdbcType=TINYINT}" +
            "     , FN_ENCRYPT(#{custRrno, jdbcType=VARCHAR})" +
            "     , FN_ENCRYPT(#{custEmail, jdbcType=VARCHAR})" +
            "     , FN_ENCRYPT(#{custBusiRegno, jdbcType=VARCHAR})" +
            "     , FN_ENCRYPT(#{custBusiplcMngno, jdbcType=VARCHAR})" +
            "     , FN_ENCRYPT(#{custCmpyNm, jdbcType=VARCHAR})" +
            "     , #{custYearIncomAmt, jdbcType=DECIMAL}, #{custJobClscd, jdbcType=VARCHAR}, #{custNhisMemGbcd, jdbcType=VARCHAR}" +
            "     , #{custBusiUpjongGbcd, jdbcType=VARCHAR}, #{custEmployGbcd, jdbcType=VARCHAR}, #{custEnterYyyymm, jdbcType=VARCHAR}" +
            "     , #{custHousOwnGbcd, jdbcType=VARCHAR}, #{custHousTypecd, jdbcType=TINYINT}, #{custCiNo, jdbcType=VARCHAR}" +
            "     , #{lnHopeDt, jdbcType=VARCHAR}, #{custCarownYn, jdbcType=VARCHAR}, #{housOwnCnt, jdbcType=INTEGER}, #{marriedYn, jdbcType=VARCHAR}" +
            "     , #{childrenCnt, jdbcType=INTEGER}, #{agoLnAmt, jdbcType=VARCHAR}, #{lnMrtgNo, jdbcType=VARCHAR}" +
            ")")
    int save(InqMasDTO.Create create);

    @Update("<script>" +
            "UPDATE TBLNS_INQ_MAS" +
            "   SET" +
            "   <if test='lnGbcd != null and lnGbcd != \"\"'>" +
            "       LN_GBCD = #{lnGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='lnReqGbcd != null and lnReqGbcd != \"\"'>" +
            "       LN_REQ_GBCD = #{lnReqGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='lnUseGbcd != null and lnUseGbcd != \"\"'>" +
            "       LN_USE_GBCD = #{lnUseGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='lnReqAmt != null and lnReqAmt != \"\"'>" +
            "       LN_REQ_AMT = #{lnReqAmt, jdbcType=DECIMAL}," +
            "   </if>" +
            "   <if test='lnReqTermMm != null and lnReqTermMm != \"\"'>" +
            "       LN_REQ_TERM_MM = #{lnReqTermMm, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='lnRtnMthCd != null and lnRtnMthCd != \"\"'>" +
            "       LN_RTN_MTH_CD = #{lnRtnMthCd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='lnRateKindGbcd != null and lnRateKindGbcd != \"\"'>" +
            "       LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='lnRateCycleCd != null and lnRateCycleCd != \"\"'>" +
            "       LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='recoverHisYn != null and recoverHisYn != \"\"'>" +
            "       RECOVER_HIS_YN = #{recoverHisYn, jdbcType=TINYINT}," +
            "   </if>" +
            "   <if test='recoverPayCmplYn != null and recoverPayCmplYn != \"\"'>" +
            "       RECOVER_PAY_CMPL_YN = #{recoverPayCmplYn, jdbcType=TINYINT}," +
            "   </if>" +
            "   <if test='custRrno != null and custRrno != \"\"'>" +
            "       CUST_RRNO = FN_ENCRYPT(#{custRrno, jdbcType=VARCHAR})," +
            "   </if>" +
            "   <if test='custEmail != null and custEmail != \"\"'>" +
            "       CUST_EMAIL = FN_ENCRYPT(#{custEmail, jdbcType=VARCHAR})," +
            "   </if>" +
            "   <if test='custBusiRegno != null and custBusiRegno != \"\"'>" +
            "       CUST_BUSI_REGNO = FN_ENCRYPT(#{custBusiRegno, jdbcType=VARCHAR})," +
            "   </if>" +
            "   <if test='custBusiplcMngno != null and custBusiplcMngno != \"\"'>" +
            "       CUST_BUSIPLC_MNGNO = FN_ENCRYPT(#{custBusiplcMngno, jdbcType=VARCHAR})," +
            "   </if>" +
            "   <if test='custCmpyNm != null and custCmpyNm != \"\"'>" +
            "       CUST_CMPY_NM = FN_ENCRYPT(#{custCmpyNm, jdbcType=VARCHAR})," +
            "   </if>" +
            "   <if test='custYearIncomAmt != null and custYearIncomAmt != \"\"'>" +
            "       CUST_YEAR_INCOM_AMT = #{custYearIncomAmt, jdbcType=DECIMAL}," +
            "   </if>" +
            "   <if test='custJobClscd != null and custJobClscd != \"\"'>" +
            "       CUST_JOB_CLSCD = #{custJobClscd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='custNhisMemGbcd != null and custNhisMemGbcd != \"\"'>" +
            "       CUST_NHIS_MEM_GBCD = #{custNhisMemGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='custBusiUpjongGbcd != null and custBusiUpjongGbcd != \"\"'>" +
            "       CUST_BUSI_UPJONG_GBCD = #{custBusiUpjongGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='custEmployGbcd != null and custEmployGbcd != \"\"'>" +
            "       CUST_EMPLOY_GBCD = #{custEmployGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='custEnterYyyymm != null and custEnterYyyymm != \"\"'>" +
            "       CUST_ENTER_YYYYMM = #{custEnterYyyymm, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='custHousOwnGbcd != null and custHousOwnGbcd != \"\"'>" +
            "       CUST_HOUS_OWN_GBCD = #{custHousOwnGbcd, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='custHousTypecd != null and custHousTypecd != \"\"'>" +
            "       CUST_HOUS_TYPECD = #{custHousTypecd, jdbcType=TINYINT}," +
            "   </if>" +
            "   <if test='lnHopeDt != null and lnHopeDt != \"\"'>" +
            "       LN_HOPE_DT = #{lnHopeDt, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='custCarownYn != null and custCarownYn != \"\"'>" +
            "       CUST_CAROWN_YN = #{custCarownYn, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='housOwnCnt != null and housOwnCnt != \"\"'>" +
            "       HOUS_OWN_CNT = #{housOwnCnt, jdbcType=INTEGER}," +
            "   </if>" +
            "   <if test='marriedYn != null and marriedYn != \"\"'>" +
            "       MARRIED_YN = #{marriedYn, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='childrenCnt != null and childrenCnt != \"\"'>" +
            "       CHILDREN_CNT = #{childrenCnt, jdbcType=INTEGER}," +
            "   </if>" +
            "   <if test='agoLnAmt != null and agoLnAmt != \"\"'>" +
            "       AGO_LN_AMT = #{agoLnAmt, jdbcType=VARCHAR}," +
            "   </if>" +
            "   <if test='lnMrtgNo != null and lnMrtgNo != \"\"'>" +
            "       LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}," +
            "   </if>" +
            "       LN_INQ_DTTM = CURRENT_TIMESTAMP" +
            " WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "   AND CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}" +
            "</script>")
    int edit(InqMasDTO.Create create);

    @Delete("DELETE FROM TBLNS_INQ_MAS" +
            " WHERE LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "   AND CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}")
    int remove(InqMasDTO.Param param);

}
