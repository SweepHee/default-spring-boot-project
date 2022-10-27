package com.bankpin.user.sns.auth.model.mapper;

import com.bankpin.user.cust.model.dto.CustAuthDtlDTO;
import com.bankpin.user.sns.auth.model.dto.SnsUserDTO;
import com.bankpin.user.sns.auth.model.type.SnsType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SnsAuthMapper {

    @Select({
            "select exists",
            "(select CUST_ID from TBCOM_CUSTMAS where CUST_ID = #{custId, jdbcType=VARCHAR})"
    })
    boolean existsById(String id);


    @Select({
            "select * from TBCOM_CUSTMAS where CUST_ID = #{id, jdbcType=VARCHAR}"
    })
    SnsUserDTO.Create findByUsername(String id);


    @Select({
            "SELECT t1.* FROM TBCOM_CUSTMAS WHERE CUST_ID = #{custId, jdbcType=VARCHAR}"
    })
    SnsUserDTO.Create findByCustId(String custId);


    @Insert({"INSERT INTO TBCOM_CUSTMAS (",
            " CUST_CI_NO, CUST_SIMPLE_PWD, CUST_ID, CUST_NM, CUST_AUTH_CD, CUST_ACTV_GBCD, CUST_EMAIL, CUST_BIRTH, CUST_GENDER",
            ") VALUE (",
            " REPLACE(UUID(),'-',''), #{password, jdbcType=VARCHAR},",
            " #{id, jdbcType=VARCHAR}, #{name, jdbcType=VARCHAR},",
            " #{custAuthCd, jdbcType=VARCHAR}, #{custActvGbcd, jdbcType=BIT},",
            " #{custEmail, jdbcType=VARCHAR}, #{custBirth, jdbcType=VARCHAR},",
            " #{custGender}",
            ")"})
    void save(SnsUserDTO.Create user);


    @Update({
            "UPDATE TBCOM_CUSTMAS SET",
            "  CUST_NM = #{name, jdbcType=VARCHAR}",
            ", CUST_EMAIL = #{custEmail, jdbcType=VARCHAR}",
            " WHERE CUST_CI_NO = #{id, jdbcType=VARCHAR}"
    })
    void update(SnsUserDTO.Create user);


    @Select({
            "select FN_ENCRYPT( #{str, jdbcType=VARCHAR} )"
    })
    String fnEncrypt(String str);

    @Select({
            "select FN_DECRYPT( #{str, jdbcType=VARCHAR} )"
    })
    String fnDecrypt(String str);


    @Select({
            "select CUST_CI_NO from TBCOM_CUSTMAS where CUST_ID = #{custId, jdbcType=VARCHAR}"
    })
    String findCustCiNoByCustId(String custId);


    @Select({
            "select * from TBCOM_CUSTMAS t1 ",
            "inner join TBCOM_CUSTSNS_DTL t2 ON t1.CUST_CI_NO = t2.CUST_CI_NO ",
            "where t1.CUST_ID = #{custCiNo, jdbcType=VARCHAR} and t2.CUST_SNS_TYPE = #{type, jdbcType=VARCHAR}"
    })
    SnsUserDTO.Create duplicateCheck(String custCiNo, SnsType type);

    @Select({
            "select * from TBCOM_CUSTMAS ",
            "where CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"
    })
    SnsUserDTO.Column findByCustCiNo(String custCiNo);


}