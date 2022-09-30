package com.bankpin.user.auth.model.mapper;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.auth.model.dto.UserDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AuthMapper
{
    @Select({"SELECT ",
            " CUST_CI_NO as id, CUST_SIMPLE_PWD as password, CUST_ID as username, CUST_AUTH_CD as authority",
            " FROM TBCOM_CUSTMAS",
            " WHERE CUST_ID = #{id, jdbcType=VARCHAR}"})
    UserAuth findByUsername(@Param("id") String id);

    @Insert({"INSERT INTO TBCOM_CUSTMAS (",
            " CUST_CI_NO, CUST_SIMPLE_PWD, CUST_ID, CUST_NM, CUST_AUTH_CD, CUST_ACTV_GBCD",
            ") VALUE (",
            " REPLACE(UUID(),'-',''), #{password, jdbcType=VARCHAR},",
            " #{id, jdbcType=VARCHAR}, #{name, jdbcType=VARCHAR},",
            " #{custAuthCd, jdbcType=VARCHAR}, #{custActvGbcd, jdbcType=BIT}",
            ")"})
    void save(UserDTO.Create user);

    @Update({"UPDATE TBCOM_CUSTMAS SET",
            " CUST_NM = #{name, jdbcType=VARCHAR},",
            " CUST_AUTH_CD = #{custAuthCd, jdbcType=VARCHAR},",
            " CUST_ACTV_GBCD = #{custActvGbcd, jdbcType=BIT}",
            " WHERE CUST_CI_NO = #{id, jdbcType=VARCHAR}"})
    void update(UserDTO.Create user);

}
