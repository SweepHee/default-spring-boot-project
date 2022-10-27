package com.bankpin.user.cust.mapper;

import com.bankpin.user.cust.model.dto.CustMasDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CustMasMapper
{
    @Select("SELECT"+
            "     , CUST_CI_NO" +
            "     , CUST_ID" +
            "     , FN_DECRYPT(CUST_NM) AS CUST_NM" +
            "     , CUST_AUTH_CD" +
            "     , CUST_ACTV_GBCD" +
            "     , FN_DECRYPT(CUST_EMAIL) AS CUST_EMAIL" +
            "     , CUST_BIRTH" +
            "     , CUST_GENDER" +
            "  FROM TBCOM_CUSTMAS"+
            " WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"+
            "   AND CUST_ACTV_GBCD = 1")
    CustMasDTO findByCustCiNo(@Param("custCiNo") String custCiNo);

    @Select("SELECT"+
            "     , CUST_CI_NO" +
            "     , CUST_ID" +
            "     , FN_DECRYPT(CUST_NM) AS CUST_NM" +
            "     , CUST_AUTH_CD" +
            "     , CUST_BIRTH" +
            "     , CUST_GENDER" +
            "  FROM TBCOM_CUSTMAS"+
            " WHERE CUST_ACTV_GBCD = #{custActvGbcd, jdbcType=TINYINT}")
    List<CustMasDTO> findAllByCustActvGbcd(boolean custActvGbcd);

}
