package com.bankpin.user.auth.model.mapper;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.auth.model.dto.UserDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AuthMapper
{
    @Select({"SELECT",
            "        CUST_CI_NO as id, CUST_SIMPLE_PWD as password, CUST_ID as username",
            "      , CUST_AUTH_CD as authority, FN_DECRYPT(CUST_NM) AS name",
            "   FROM TBCOM_CUSTMAS",
            "  WHERE CUST_ID = #{id, jdbcType=VARCHAR}",
            "    AND CUST_ACTV_GBCD = 1"})
    UserAuth findByUsername(@Param("id") String id);

    @Insert({"INSERT INTO TBCOM_CUSTMAS (",
            " CUST_CI_NO, CUST_SIMPLE_PWD,",
            " CUST_ID,",
            " CUST_NM,",
            " CUST_AUTH_CD, CUST_ACTV_GBCD,",
            " CUST_EMAIL,",
            " CUST_BIRTH, CUST_GENDER",
            ") VALUE (",
            " REPLACE(UUID(),'-',''), #{password, jdbcType=VARCHAR},",
            " #{id, jdbcType=VARCHAR},",
            " FN_ENCRYPT(#{name, jdbcType=VARCHAR}),",
            " #{custAuthCd, jdbcType=VARCHAR}, #{custActvGbcd, jdbcType=TINYINT},",
            " FN_ENCRYPT(#{custEmail, jdbcType=VARCHAR}),",
            " #{custBirth, jdbcType=VARCHAR}, #{custGender, jdbcType=VARCHAR}",
            ")"})
    void save(UserDTO.Create user);

    @Update({"UPDATE TBCOM_CUSTMAS SET",
            " CUST_NM = FN_ENCRYPT(#{name, jdbcType=VARCHAR}),",
            " CUST_AUTH_CD = #{custAuthCd, jdbcType=VARCHAR},",
            " CUST_ACTV_GBCD = #{custActvGbcd, jdbcType=BIT},",
            " CUST_EMAIL = FN_ENCRYPT(#{custAuthCd, jdbcType=VARCHAR}),",
            " CUST_BIRTH = #{custBirth, jdbcType=VARCHAR},",
            " CUST_GENDER = #{custGender, jdbcType=VARCHAR}",
            " WHERE CUST_CI_NO = #{id, jdbcType=VARCHAR}"})
    void update(UserDTO.Create user);

}
