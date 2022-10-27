package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.CooconCustAgreeLstDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CooconCustAgreeLstMapper {

    @Select(
            "SELECT * FROM TBCOM_CUSTAGREE_LST WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR} GROUP BY CUST_AGREE_ITM_CD"
    )
    List<CooconCustAgreeLstDTO.Create> findByCustCiNo(String custciNo);

}
