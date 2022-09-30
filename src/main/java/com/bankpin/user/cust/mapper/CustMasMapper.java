package com.bankpin.user.cust.mapper;

import com.bankpin.user.cust.model.dto.CustMasDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CustMasMapper
{
    @Select("SELECT *"+
            "  FROM TBCOM_CUSTMAS"+
            " WHERE cust_ci_no = #{custCiNo, jdbcType=VARCHAR}"+
            "   AND cust_actv_gbcd = 1")
    CustMasDTO findByCustCiNo(@Param("custCiNo") String custCiNo);

    @Select("SELECT *"+
            "  FROM TBCOM_CUSTMAS"+
            " WHERE cust_actv_gbcd = #{custActvGbcd, jdbcType=BIT}")
    List<CustMasDTO> findAllByCustActvGbcd(boolean custActvGbcd);

}
