package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.CooconLogDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CooconLogMapper {

    @Insert(
                "INSERT INTO TBCOM_API_COOCON_LOG SET "+
                "  CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}"+
                ", API_URL = #{apiUrl, jdbcType=VARCHAR}"+
                ", API_TYPE = #{apiType, jdbcType=VARCHAR}"+
                ", API_IP_ADDR = #{apiIpAddr, jdbcType=VARCHAR}"+
                ", API_INPUT = #{apiInput, jdbcType=VARCHAR}"+
                ", API_OUTPUT = #{apiOutput, jdbcType=VARCHAR} "
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(CooconLogDTO.Create create);


    @Update({"<script>" +
            "UPDATE TBCOM_API_COOCON_LOG " +
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
                "<if test='apiInput != null and apiInput != \"\"'>" +
                "API_INPUT = #{apiInput, jdbcType=VARCHAR}," +
                "</if>" +
                "<if test='apiOutput != null and apiOutput != \"\"'>" +
                "API_OUTPUT = #{apiOutput, jdbcType=VARCHAR}," +
                "</if>" +
            "</trim>"+
            "WHERE " +
            "  ID = #{id, jdbcType=BIGINT}" +
            "</script>"
    })
    void update(CooconLogDTO.Create create);
}
