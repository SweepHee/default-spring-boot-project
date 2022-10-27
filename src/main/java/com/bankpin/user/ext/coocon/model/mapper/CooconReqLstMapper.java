package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.ReqLstDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CooconReqLstMapper {

    @Insert(
            "INSERT INTO TBLNS_REQ_LST SET "+
            "  LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR} "+
            ", FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
//            ", BANK_CD = #{bankCd, jdbcType=VARCHAR} "+
//            ", BANK_BRCH_CD = #{bankBrchCd, jdbcType=VARCHAR} "+
            ", LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR} " +
            ", BR_PAGE_URL = #{brPageUrl, jdbcType=VARCHAR}" +
            ", CALLBACK_TEL_NO = #{callbackTelNo, jdbcType=VARCHAR}"
    )
    void save(ReqLstDTO.Create create);


}
