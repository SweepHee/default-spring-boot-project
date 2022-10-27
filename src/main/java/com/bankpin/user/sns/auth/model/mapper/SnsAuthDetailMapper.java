package com.bankpin.user.sns.auth.model.mapper;

import com.bankpin.user.sns.auth.model.dto.SnsUserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SnsAuthDetailMapper {

    @Insert({
            "INSERT INTO TBCOM_CUSTSNS_DTL SET",
            "  CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}",
            ", CUST_SNS_TYPE = #{custSnsType, jdbcType=VARCHAR}"
    })
    void save(SnsUserDTO.Detail detail);

}