package com.bankpin.user.mrtg.mapper;

import com.bankpin.user.mrtg.model.dto.MrtgevalInfoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MrtgevalInfoMapper
{
    @Select("SELECT" +
            "       LN_MRTG_NO" +
            "     , MRTG_SEQ_NO" +
            "     , MRTG_GBCD" +
            "     , BLD_MNG_NO" +
            "     , BLD_NM" +
            "     , SIDO_NM" +
            "     , SIGUNGU_NM" +
            "     , DONG_NM" +
            "     , EAR_AREA" +
            "     , MAIN_BUNJI" +
            "     , SUB_BUNJI" +
            "     , MKT_HIGH_AMT" +
            "     , MKT_AVG_AMT" +
            "     , MKT_LOW_AMT" +
            "     , APPR_EVAL_AMT" +
            "     , MRTG_POS_X_VAL" +
            "     , MRTG_POS_Y_VAL" +
            "     , FST_ENR_DTTM" +
            "     , FST_ENR_PGM" +
            "     , LST_CHG_DTTM" +
            "     , LST_CHG_PGM" +
            "  FROM TBLNS_MRTGEVAL_INFO" +
            " WHERE LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}" +
            "   AND MRTG_SEQ_NO = #{mrtgSeqNo, jdbcType=INTEGER}")
    MrtgevalInfoDTO.Detail findByLnMrtgNo(
            @Param("lnMrtgNo") String lnMrtgNo,
            @Param("mrtgSeqNo") int mrtgSeqNo);

    @Select("SELECT" +
            "       LN_MRTG_NO" +
            "     , MRTG_SEQ_NO" +
            "     , MRTG_GBCD" +
            "     , BLD_MNG_NO" +
            "     , BLD_NM" +
            "     , SIDO_NM" +
            "     , SIGUNGU_NM" +
            "     , DONG_NM" +
            "     , EAR_AREA" +
            "     , MAIN_BUNJI" +
            "     , SUB_BUNJI" +
            "     , MKT_HIGH_AMT" +
            "     , MKT_AVG_AMT" +
            "     , MKT_LOW_AMT" +
            "     , APPR_EVAL_AMT" +
            "     , MRTG_POS_X_VAL" +
            "     , MRTG_POS_Y_VAL" +
            "     , FST_ENR_DTTM" +
            "     , FST_ENR_PGM" +
            "     , LST_CHG_DTTM" +
            "     , LST_CHG_PGM" +
            "  FROM TBLNS_MRTGEVAL_INFO" +
            " WHERE MRTG_GBCD = #{mrtgGbcd, jdbcType=VARCHAR}")
    List<MrtgevalInfoDTO.Item> findAll(@Param("mrtgGbcd") String mrtgGbcd);

    @Insert("INSERT INTO TBLNS_MRTGEVAL_INFO (" +
            "       LN_MRTG_NO" +
            "     , MRTG_SEQ_NO" +
            "     , MRTG_GBCD" +
            "     , BLD_MNG_NO" +
            "     , BLD_NM" +
            "     , SIDO_NM" +
            "     , SIGUNGU_NM" +
            "     , DONG_NM" +
            "     , EAR_AREA" +
            "     , MAIN_BUNJI" +
            "     , SUB_BUNJI" +
            "     , MKT_HIGH_AMT" +
            "     , MKT_AVG_AMT" +
            "     , MKT_LOW_AMT" +
            "     , APPR_EVAL_AMT" +
            "     , MRTG_POS_X_VAL" +
            "     , MRTG_POS_Y_VAL" +
            "     , FST_ENR_DTTM" +
            "     , FST_ENR_PGM" +
            "     , LST_CHG_DTTM" +
            "     , LST_CHG_PGM" +
            ") VALUES (" +
            "       #{lnMrtgNo, jdbcType=VARCHAR}" +
            "     , #{mrtgSeqNo, jdbcType=INTEGER}" +
            "     , #{mrtgGbcd, jdbcType=VARCHAR}" +
            "     , #{bldMngNo, jdbcType=VARCHAR}" +
            "     , #{bldNm, jdbcType=VARCHAR}" +
            "     , #{sidoNm, jdbcType=VARCHAR}" +
            "     , #{sigunguNm, jdbcType=VARCHAR}" +
            "     , #{dongNm, jdbcType=VARCHAR}" +
            "     , #{earArea, jdbcType=DECIMAL}" +
            "     , #{mainBunji, jdbcType=VARCHAR}" +
            "     , #{subBunji, jdbcType=VARCHAR}" +
            "     , #{mktHighAmt, jdbcType=DECIMAL}" +
            "     , #{mktAvgAmt, jdbcType=DECIMAL}" +
            "     , #{mktLowAmt, jdbcType=DECIMAL}" +
            "     , #{apprEvalAmt, jdbcType=DECIMAL}" +
            "     , #{mrtgPosXVal, jdbcType=VARCHAR}" +
            "     , #{mrtgPosYVal, jdbcType=VARCHAR}" +
            "     , #{fstEnrDttm, jdbcType=TIMESTAMP}" +
            "     , #{fstEnrPgm, jdbcType=VARCHAR}" +
            "     , #{lstChgDttm, jdbcType=TIMESTAMP}" +
            "     , #{lstChgPgm, jdbcType=VARCHAR}" +
            ")")
    int save(MrtgevalInfoDTO.Create create);

    @Update("UPDATE TBLNS_MRTGEVAL_INFO SET" +
            "       MRTG_GBCD = #{mrtgGbcd, jdbcType=VARCHAR}" +
            "     , BLD_MNG_NO = #{bldMngNo, jdbcType=VARCHAR}" +
            "     , BLD_NM = #{bldNm, jdbcType=VARCHAR}" +
            "     , SIDO_NM = #{sidoNm, jdbcType=VARCHAR}" +
            "     , SIGUNGU_NM = #{sigunguNm, jdbcType=VARCHAR}" +
            "     , DONG_NM = #{dongNm, jdbcType=VARCHAR}" +
            "     , EAR_AREA = #{earArea, jdbcType=DECIMAL}" +
            "     , MAIN_BUNJI = #{mainBunji, jdbcType=VARCHAR}" +
            "     , SUB_BUNJI = #{subBunji, jdbcType=VARCHAR}" +
            "     , MKT_HIGH_AMT = #{mktHighAmt, jdbcType=DECIMAL}" +
            "     , MKT_AVG_AMT = #{mktAvgAmt, jdbcType=DECIMAL}" +
            "     , MKT_LOW_AMT = #{mktLowAmt, jdbcType=DECIMAL}" +
            "     , APPR_EVAL_AMT = #{apprEvalAmt, jdbcType=DECIMAL}" +
            "     , MRTG_POS_X_VAL = #{mrtgPosXVal, jdbcType=VARCHAR}" +
            "     , MRTG_POS_Y_VAL = #{mrtgPosYVal, jdbcType=VARCHAR}" +
            "     , FST_ENR_DTTM = #{fstEnrDttm, jdbcType=TIMESTAMP}" +
            "     , FST_ENR_PGM = #{fstEnrPgm, jdbcType=VARCHAR}" +
            "     , LST_CHG_DTTM = #{lstChgDttm, jdbcType=TIMESTAMP}" +
            "     , LST_CHG_PGM = #{lstChgPgm, jdbcType=VARCHAR}" +
            " WHERE LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}" +
            "   AND MRTG_SEQ_NO = #{mrtgSeqNo, jdbcType=INTEGER}")
    int edit(MrtgevalInfoDTO.Create create);

    @Delete("DELETE FROM TBLNS_MRTGEVAL_INFO" +
            " WHERE LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}" +
            "   AND MRTG_SEQ_NO = #{mrtgSeqNo, jdbcType=INTEGER}")
    int remove(MrtgevalInfoDTO.Param param);

}
