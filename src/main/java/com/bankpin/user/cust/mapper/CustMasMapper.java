package com.bankpin.user.cust.mapper;

import com.bankpin.user.cust.model.dto.CustMasDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CustMasMapper
{
    @Select("SELECT * FROM user WHERE custCiNo = #{custCiNo, jdbcType=VARCHAR}")
    CustMasDTO findByCustCiNo(@Param("custCiNo") String custCiNo);

//    void save()
}
