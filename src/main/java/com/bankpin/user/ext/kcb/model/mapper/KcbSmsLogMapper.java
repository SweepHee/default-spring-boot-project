package com.bankpin.user.ext.kcb.model.mapper;

import com.bankpin.user.ext.kcb.model.dto.KcbSmsLogDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface KcbSmsLogMapper {

    @Insert(
            "INSERT INTO TBCOM_API_KCBSMS_LOG SET "+
                    "  CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"+
                    ", API_URL = #{apiUrl, jdbcType=VARCHAR}"+
                    ", API_TYPE = #{apiType, jdbcType=VARCHAR}"+
                    ", API_IP_ADDR = #{apiIpAddr, jdbcType=VARCHAR}"+
                    ", API_CNTN = FN_ENCRYPT(#{apiCntn, jdbcType=VARCHAR})"+
                    ", API_OUT_CNTN = FN_ENCRYPT(#{apiOutCntn, jdbcType=VARCHAR}) "
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long save(KcbSmsLogDTO.Create create);

    @Update({"<script>" +
            "UPDATE TBCOM_API_KCBSMS_LOG " +
            "<trim prefix='SET' suffixOverrides=','>"+
            "<if test='custCiNo != null and custCiNo != \"\"'>" +
            "CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}," +
            "</if>" +
            "<if test='apiUrl != null and apiUrl != \"\"'>" +
            "API_URL = #{apiUrl, jdbcType=VARCHAR}," +
            "</if>" +
            "<if test='apiType != null and apiType != \"\"'>" +
            "API_TYPE = #{apiType, jdbcType=VARCHAR}," +
            "</if>" +
            "<if test='apiIpAddr != null and apiIpAddr != \"\"'>" +
            "API_IP_ADDR = #{apiIpAddr, jdbcType=VARCHAR}," +
            "</if>" +
            "<if test='apiCntn != null and apiCntn != \"\"'>" +
            "API_CNTN = FN_ENCRYPT(#{apiCntn, jdbcType=VARCHAR})," +
            "</if>" +
            "<if test='apiOutCntn != null and apiOutCntn != \"\"'>" +
            "API_OUT_CNTN = FN_ENCRYPT(#{apiOutCntn, jdbcType=VARCHAR})," +
            "</if>" +
            "</trim>"+
            "WHERE " +
            "  ID = #{id, jdbcType=BIGINT}" +
            "</script>"
    })
    void update(KcbSmsLogDTO.Create create);

}
