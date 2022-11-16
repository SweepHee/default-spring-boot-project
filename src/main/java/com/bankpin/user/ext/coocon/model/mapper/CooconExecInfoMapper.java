package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.ExecInfoDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CooconExecInfoMapper {

    @Select(
            "SELECT EXISTS(" +
            "SELECT * FROM TBLNS_EXEC_INFO " +
            "WHERE" +
            "    LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}" +
            "AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
            "AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"+
            ")"
    )
    boolean existsByLnReqNoAndFintecOrgMngnoAndLnPrdtCd(ExecInfoDTO.Create create);

    @Select(
            "SELECT EXISTS(" +
            "SELECT * FROM TBLNS_EXEC_INFO" +
            " WHERE " +
            "     LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR} " +
            " AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR} "+
            " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR} "+
            " AND LN_CNSL_YN = 'Y'" +
            ")"
    )
    boolean isCancel(ExecInfoDTO.Create param);

    @Update(
            "UPDATE TBLNS_EXEC_INFO SET "+
            "  LN_CNSL_YN = #{lnCnslYn, jdbcType=VARCHAR}"+
            ", LN_CNSL_REQ_DTTM = NOW() "+
            "WHERE "+
            "LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"+
            " AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
            " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"
    )
    void cancel(ExecInfoDTO.Create cancel);

    @Update(
            "UPDATE TBLNS_EXEC_INFO SET "+
                "  LN_REQ_YN = #{lnReqYn, jdbcType=VARCHAR}"+
                ", LN_REQ_DTTM = #{lnReqDttm, jdbcType=VARCHAR}"+
                ", BANK_CD = #{bankCd, jdbcType=VARCHAR}"+
                ", BANK_BRCH_CD = #{bankBrchCd, jdbcType=VARCHAR}"+
                ", LN_PRDT_NM = #{lnPrdtNm, jdbcType=VARCHAR}"+
                ", LN_AMT = #{lnAmt, jdbcType=VARCHAR}"+
                ", LST_LN_LMT_AMT = #{lstLnLmtAmt, jdbcType=VARCHAR}"+
                ", LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}"+
                ", STRD_RATE = #{strdRate, jdbcType=VARCHAR}"+
                ", APPLY_RATE = #{applyRate, jdbcType=VARCHAR}"+
                ", PRI_RATE_RSN_CNTN = #{priRateRsnCntn, jdbcType=VARCHAR}"+
                ", PRI_LMT_AMT = #{priLmtAmt, jdbcType=VARCHAR}"+
                ", PRI_RATE = #{priRate, jdbcType=VARCHAR}"+
                ", LN_REQ_DT = #{lnReqDt, jdbcType=VARCHAR}"+
                ", LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}"+
                ", LN_AUTH_DT = #{lnAuthDt, jdbcType=VARCHAR} "+
                "WHERE " +
                "     LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"+
                " AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
                " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"
    )
    void update(ExecInfoDTO.Create create);

    @Insert(
            "INSERT INTO TBLNS_EXEC_INFO SET "+
            "  LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"+
            ", FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
            ", LN_REQ_YN = #{lnReqYn, jdbcType=VARCHAR}"+
            ", LN_REQ_DTTM = #{lnReqDttm, jdbcType=VARCHAR}"+
            ", BANK_CD = #{bankCd, jdbcType=VARCHAR}"+
            ", BANK_BRCH_CD = #{bankBrchCd, jdbcType=VARCHAR}"+
            ", LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"+
            ", LN_PRDT_NM = #{lnPrdtNm, jdbcType=VARCHAR}"+
            ", LN_AMT = #{lnAmt, jdbcType=VARCHAR}"+
            ", LST_LN_LMT_AMT = #{lstLnLmtAmt, jdbcType=VARCHAR}"+
            ", LN_RATE_KIND_GBCD = #{lnRateKindGbcd, jdbcType=VARCHAR}"+
            ", STRD_RATE = #{strdRate, jdbcType=VARCHAR}"+
            ", APPLY_RATE = #{applyRate, jdbcType=VARCHAR}"+
            ", PRI_RATE_RSN_CNTN = #{priRateRsnCntn, jdbcType=VARCHAR}"+
            ", PRI_LMT_AMT = #{priLmtAmt, jdbcType=VARCHAR}"+
            ", PRI_RATE = #{priRate, jdbcType=VARCHAR}"+
            ", LN_REQ_DT = #{lnReqDt, jdbcType=VARCHAR}"+
            ", LN_RATE_CYCLE_CD = #{lnRateCycleCd, jdbcType=VARCHAR}"+
            ", LN_AUTH_DT = #{lnAuthDt, jdbcType=VARCHAR}"
    )
    void save(ExecInfoDTO.Create create);


}




















