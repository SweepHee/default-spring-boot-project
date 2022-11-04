package com.bankpin.user.cust.mapper;

import com.bankpin.user.cust.model.dto.CustAuthDtlDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface CustAuthDtlMapper
{
    @Select("SELECT "+
            "     , CUST_CI_NO"+
            "     , LN_REQ_NO"+
            "     , SELF_AUTH_METH_CD"+
            "     , SELF_AGREE_DTTM"+
            "     , SELF_AGREE_YN"+
            "     , TELE_CD"+
            "     , AUTH_ORG_UNICD"+
            "     , CUST_CPHONE_NO"+
            "  FROM TBCOM_CUSTAUTH_DTL"+
            " WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"+
            "   AND LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}")
    CustAuthDtlDTO.Detail findByCustCiNoAndLnReqNo(String custCiNo, String lnReqNo);

    @Select("SELECT "+
            "     , CUST_CI_NO"+
            "     , LN_REQ_NO"+
            "     , SELF_AUTH_METH_CD"+
            "     , SELF_AGREE_DTTM"+
            "     , SELF_AGREE_YN"+
            "     , TELE_CD"+
            "     , AUTH_ORG_UNICD"+
            "     , CUST_CPHONE_NO"+
            "  FROM TBCOM_CUSTAUTH_DTL"+
            " WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}")
    List<CustAuthDtlDTO.Item> findAllByCustCiNo(String custCiNo);

    @Insert("INSERT INTO TBCOM_CUSTAUTH_DTL ("+
            " CUST_CI_NO, LN_REQ_NO, SELF_AUTH_METH_CD, SELF_AGREE_DTTM, SELF_AGREE_YN, TELE_CD, AUTH_ORG_UNICD, CUST_CPHONE_NO"+
            " ) VALUES ("+
            " #{custCiNo, jdbcType=VARCHAR}, #{lnReqNo, jdbcType=VARCHAR}, #{selfAuthMethCd, jdbcType=VARCHAR},"+
            " #{selfAgreeDttm, jdbcType=VARCHAR}, #{selfAgreeYn, jdbcType=VARCHAR}, #{teleCd, jdbcType=VARCHAR},"+
            " #{authOrgUnicd, jdbcType=VARCHAR}, FN_ENCRYPT(#{custCphoneNo, jdbcType=VARCHAR})"+
            " )")
    int save(CustAuthDtlDTO.Create create);

    @Update("UPDATE TBCOM_CUSTAUTH_DTL SET"+
            " , SELF_AUTH_METH_CD = #{selfAuthMethCd, jdbcType=VARCHAR}"+
            " , SELF_AGREE_DTTM = #{selfAgreeDttm, jdbcType=VARCHAR}"+
            " , SELF_AGREE_YN = #{selfAgreeYn, jdbcType=VARCHAR}"+
            " , TELE_CD = #{teleCd, jdbcType=VARCHAR}"+
            " , AUTH_ORG_UNICD = #{authOrgUnicd, jdbcType=VARCHAR}"+
            " , CUST_CPHONE_NO = #{custCphoneNo, jdbcType=VARCHAR}"+
            " WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"+
            "   AND LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}")
    int update(CustAuthDtlDTO.Create update);



    @Select(
            "SELECT "+
            " if (count(LN_REQ_NO) != 0, LPAD(MAX(CAST(LN_REQ_NO AS UNSIGNED))+1,14,'0'), '00000000000000')"+
            " FROM TBCOM_CUSTAUTH_DTL"
    )
    String findByMaxLnReqNo();

    @Select(
            "SELECT * FROM TBCOM_CUSTAUTH_DTL"+
            " WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR} AND SELF_AUTH_METH_CD = #{selfAuthMethCd, jdbcType=VARCHAR}"
    )
    CustAuthDtlDTO.Detail findByCustCiNoAndSelfAuthMethCd(String custCiNo, String selfAuthMethCd);
}
