package com.bankpin.user.code.mapper;

import com.bankpin.user.code.model.dto.BankDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BankMapper
{
    @Select("SELECT" +
            "       BANK_CD" +
            "     , BANK_NM" +
            "     , BANK_BRCH_CD" +
            "     , BANK_ADDR" +
            "  FROM TBCOM_BANK" +
            " ORDER BY BANK_NM ASC")
    List<BankDTO.Item> findAll(BankDTO.Param param);

    @Select("SELECT" +
            "       BANK_CD" +
            "     , BANK_NM" +
            "     , BANK_BRCH_CD" +
            "     , BANK_ADDR" +
            "  FROM TBCOM_BANK" +
            " WHERE BANK_CD = #{bankCd, jdbcType=VARCHAR}")
    BankDTO.Detail findByBankCd(BankDTO.Param param);

    /*
    @Insert("")
    int add(BankDTO.Param param);

    @Update("")
    int edit(BankDTO.Param param);

    @Delete("")
    int remove(BankDTO.Param param);
    */

}
