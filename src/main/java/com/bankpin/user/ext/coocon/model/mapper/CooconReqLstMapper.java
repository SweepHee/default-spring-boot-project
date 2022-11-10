package com.bankpin.user.ext.coocon.model.mapper;

import com.bankpin.user.ext.coocon.model.dto.ReqLstDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CooconReqLstMapper {



    @Select(
            "SELECT EXISTS(" +
                    "SELECT * FROM TBLNS_REQ_LST WHERE"+
                    "     LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"+
                    " AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
                    " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"+
                    ")"
    )
    boolean existsByLnReqNoAndFintecOrgMngnoAndLnPrdtCd(ReqLstDTO.Create create);


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



    @Update(
            "UPDATE TBLNS_REQ_LST SET"+
//            ", BANK_CD = #{bankCd, jdbcType=VARCHAR} "+
//            ", BANK_BRCH_CD = #{bankBrchCd, jdbcType=VARCHAR} "+
            "  BR_PAGE_URL = #{brPageUrl, jdbcType=VARCHAR}" +
            ", CALLBACK_TEL_NO = #{callbackTelNo, jdbcType=VARCHAR}"+
            " WHERE " +
            "     LN_REQ_NO = #{lnReqNo, jdbcType=VARCHAR}"+
            " AND FINTEC_ORG_MNGNO = #{fintecOrgMngno, jdbcType=VARCHAR}"+
            " AND LN_PRDT_CD = #{lnPrdtCd, jdbcType=VARCHAR}"
    )
    void update(ReqLstDTO.Create create);
}
