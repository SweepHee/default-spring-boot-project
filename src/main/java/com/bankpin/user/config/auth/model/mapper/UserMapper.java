package com.bankpin.user.config.auth.model.mapper;

import com.bankpin.user.config.auth.model.dto.CustomerAuth;
import com.bankpin.user.config.auth.model.dto.CustomerDTO;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper
{
    @Select({"SELECT ",
            " id, password, username, authority, is_deleted, deleted, deleted_date, created_date, updated_date",
            " FROM customer WHERE username = #{username, jdbcType=VARCHAR}"})
    CustomerAuth findByUsername(@Param("username") String username);

    @Insert({"INSERT INTO customer (",
            " id, username, password, authority, is_deleted, deleted, created_date, updated_date",
            ") VALUE (",
            " '<key-value>', #{id, jdbcType=VARCHAR}, #{password, jdbcType=VARCHAR}, #{authority, jdbcType=VARCHAR},",
            " 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP",
            ")"})
    void save(CustomerDTO.Create user);

    @Update({"UPDATE customer SET",
            " authority = #{authority, jdbcType=VARCHAR}",
            " updated_date = CURRENT_TIMESTAMP",
            " WHERE id = #{id, jdbcType=VARCHAR}"})
    void update(CustomerDTO.Create user);

}
