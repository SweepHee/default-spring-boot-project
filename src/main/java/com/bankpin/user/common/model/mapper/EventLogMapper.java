package com.bankpin.user.common.model.mapper;

import com.bankpin.user.common.model.dto.EventLogDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventLogMapper
{
    @Select("SELECT" +
            "       ID, CUST_CI_NO, EVENT_URL, EVENT_ACTION" +
            "     , EVENT_IP_ADDR, EVENT_DEVICE, EVENT_DATETIME" +
            "  FROM TBCOM_EVENT_LOG" +
            " WHERE ID = #{id, jdbcType=BIGINT}")
    EventLogDTO.Detail findById(EventLogDTO.Param param);

    @Select("SELECT" +
            "       ID, CUST_CI_NO, EVENT_URL, EVENT_ACTION" +
            "     , EVENT_IP_ADDR, EVENT_DEVICE, EVENT_DATETIME" +
            "  FROM TBCOM_EVENT_LOG" +
            " WHERE CUST_CI_NO = #{custCiNo, jdbcType=VARCHAR}" +
            " ORDER BY EVENT_DATETIME DESC")
    List<EventLogDTO.Item> findAllByCustCiNo(EventLogDTO.Param param);

    @Insert("INSERT INTO TBCOM_EVENT_LOG (" +
            "       CUST_CI_NO, EVENT_URL, EVENT_ACTION" +
            "     , EVENT_IP_ADDR, EVENT_DEVICE" +
            ") VALUES (" +
            "       #{custCiNo, jdbcType=VARCHAR}, #{eventUrl, jdbcType=VARCHAR}, #{eventAction, jdbcType=VARCHAR}" +
            "     , #{eventIpAddr, jdbcType=VARCHAR}, #{eventDevice, jdbcType=VARCHAR}" +
            ")")
    int save(EventLogDTO.Create create);

}
