package com.bankpin.user.terms.model.mapper;

import com.bankpin.user.terms.model.dto.TermsAgreeDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TermsAgreeMapper {


    @Select(
            "SELECT EXISTS" +
                    "(SELECT * FROM terms_agree " +
                    " WHERE user_id = #{userId, jdbcType=VARCHAR} " +
                    " AND terms_type = #{termsType, jdbcType=VARCHAR}) "
    )
    boolean existsByUserIdAndTermsType(TermsAgreeDTO.Create create);

    @Insert(
            "INSERT INTO terms_agree SET "+
            "  user_id = #{userId, jdbcType=VARCHAR}"+
            ", terms_type = #{termsType, jdbcType=VARCHAR}"+
            ", accept = #{accept, jdbcType=VARCHAR}"+
            ", created_date = NOW()"

    )
    void save(TermsAgreeDTO.Create create);

    @Update(
            "UPDATE terms_agree SET "+
            "  user_id = #{userId, jdbcType=VARCHAR}"+
            ", terms_type = #{termsType, jdbcType=VARCHAR}"+
            ", accept = #{accept, jdbcType=VARCHAR}"+
            ", updated_date = NOW()"+
            "    WHERE"+
            "    user_id = #{userId, jdbcType=VARCHAR}"+
            "AND terms_type = #{termsType, jdbcType=VARCHAR}"
    )
    void update(TermsAgreeDTO.Create create);

    @Delete(
            "DELETE FROM terms_agree WHERE "+
            "    user_id = #{userId, jdbcType=VARCHAR}"+
            "AND terms_type = #{termsType, jdbcType=VARCHAR}"
    )
    void deleteByIdAndTermsType(TermsAgreeDTO.Create create);
}