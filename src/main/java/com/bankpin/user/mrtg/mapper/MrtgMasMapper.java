package com.bankpin.user.mrtg.mapper;

import com.bankpin.user.mrtg.model.dto.MrtgMasDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface MrtgMasMapper
{
    @Select("SELECT" +
            "       LN_MRTG_NO" +
            "     , MRTG_KIND_CD" +
            "     , EAR_AREA_SUM" +
            "     , MKT_HIGH_SUM_AMT" +
            "     , MKT_AVG_SUM_AMT" +
            "     , MKT_LOW_SUM_AMT" +
            "     , APPR_EVAL_SUM_AMT" +
            "     , FST_ENR_DTTM" +
            "     , FST_ENR_PGM" +
            "     , LST_CHG_DTTM" +
            "     , LST_CHG_PGM" +
            "  FROM TBLNS_MRTG_MAS" +
            " WHERE LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}")
    MrtgMasDTO.Detail findByLnMrtgNo(@Param("lnMrtgNo") String lnMrtgNo);

    @Select("SELECT" +
            "       LN_MRTG_NO" +
            "     , MRTG_KIND_CD" +
            "     , EAR_AREA_SUM" +
            "     , MKT_HIGH_SUM_AMT" +
            "     , MKT_AVG_SUM_AMT" +
            "     , MKT_LOW_SUM_AMT" +
            "     , APPR_EVAL_SUM_AMT" +
            "     , FST_ENR_DTTM" +
            "     , FST_ENR_PGM" +
            "     , LST_CHG_DTTM" +
            "     , LST_CHG_PGM" +
            "  FROM TBLNS_MRTG_MAS" +
            " WHERE MRTG_KIND_CD = #{mrtgKindCd, jdbcType=VARCHAR}")
    List<MrtgMasDTO.Item> findAll(@Param("mrtgKindCd") String mrtgKindCd);

    @Insert("INSERT INTO TBLNS_MRTG_MAS (" +
            "       LN_MRTG_NO" +
            "     , MRTG_KIND_CD" +
            "     , EAR_AREA_SUM" +
            "     , MKT_HIGH_SUM_AMT" +
            "     , MKT_AVG_SUM_AMT" +
            "     , MKT_LOW_SUM_AMT" +
            "     , APPR_EVAL_SUM_AMT" +
            "     , FST_ENR_DTTM" +
            "     , FST_ENR_PGM" +
            "     , LST_CHG_DTTM" +
            "     , LST_CHG_PGM" +
            ") VALUES (" +
            "       #{lnMrtgNo, jdbcType=VARCHAR}" +
            "     , #{mrtgKindCd, jdbcType=VARCHAR}" +
            "     , #{earAreaSum, jdbcType=DECIMAL}" +
            "     , #{mktHighSumAmt, jdbcType=DECIMAL}" +
            "     , #{mktAvgSumAmt, jdbcType=DECIMAL}" +
            "     , #{mktLowSumAmt, jdbcType=DECIMAL}" +
            "     , #{apprEvalSumAmt, jdbcType=DECIMAL}" +
            "     , #{fstEnrDttm, jdbcType=TIMESTAMP}" +
            "     , #{fstEnrPgm, jdbcType=VARCHAR}" +
            "     , #{lstChgDttm, jdbcType=TIMESTAMP}" +
            "     , #{lstChgPgm, jdbcType=VARCHAR}" +
            ")")
    int save(MrtgMasDTO.Create create);

    @Update("UPDATE TBLNS_MRTG_MAS SET" +
            "     , MRTG_KIND_CD = #{mrtgKindCd, jdbcType=VARCHAR}" +
            "     , EAR_AREA_SUM = #{earAreaSum, jdbcType=DECIMAL}" +
            "     , MKT_HIGH_SUM_AMT = #{mktHighSumAmt, jdbcType=DECIMAL}" +
            "     , MKT_AVG_SUM_AMT = #{mktAvgSumAmt, jdbcType=DECIMAL}" +
            "     , MKT_LOW_SUM_AMT = #{mktLowSumAmt, jdbcType=DECIMAL}" +
            "     , APPR_EVAL_SUM_AMT = #{apprEvalSumAmt, jdbcType=DECIMAL}" +
            "     , FST_ENR_DTTM = #{fstEnrDttm, jdbcType=TIMESTAMP}" +
            "     , FST_ENR_PGM = #{fstEnrPgm, jdbcType=VARCHAR}" +
            "     , LST_CHG_DTTM = #{lstChgDttm, jdbcType=TIMESTAMP}" +
            "     , LST_CHG_PGM = #{lstChgPgm, jdbcType=VARCHAR}" +
            " WHERE LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}")
    int edit(MrtgMasDTO.Create create);

    @Delete("DELETE FROM TBLNS_MRTG_MAS" +
            " WHERE LN_MRTG_NO = #{lnMrtgNo, jdbcType=VARCHAR}")
    int remove(MrtgMasDTO.Param param);

}
