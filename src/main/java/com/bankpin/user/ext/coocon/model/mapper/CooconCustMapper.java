package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.CooconCustAuthDtlDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CooconCustMapper {

    @Select(
            "SELECT * FROM TBCOM_CUSTAUTH_DTL WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"
    )
    CooconCustAuthDtlDTO.Detail findAuthDtlByCustCiNo(String custCiNo);


    @Select(
            "SELECT * FROM TBCOM_CUSTAUTH_DTL " +
            "WHERE " +
            "CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR} " +
            "AND LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"
    )
    CooconCustAuthDtlDTO.Detail findAuthDtlByCustCiNoAndLnReqNo(String custCiNo, String lnReqNo);
}
