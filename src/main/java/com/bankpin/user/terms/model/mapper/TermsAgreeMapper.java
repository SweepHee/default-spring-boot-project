package com.bankpin.user.terms.model.mapper;

import com.bankpin.user.terms.model.dto.TermsAgreeDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TermsAgreeMapper {


    @Select(
            "SELECT id, user_id, UPPER(terms_type) as terms_type, accept, created_date, updated_date FROM terms_agree WHERE "+
            " user_id = #{userId, jdbcType=VARCHAR}"
    )
    List<TermsAgreeDTO.Create> findAllByUserId(String userId);


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
            ", created_date = NOW()"+
            ", updated_date = NOW()"

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