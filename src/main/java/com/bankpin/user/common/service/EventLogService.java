package com.bankpin.user.common.service;

import com.bankpin.user.common.model.dto.EventLogDTO;
import com.bankpin.user.common.model.mapper.EventLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EventLogService
{
    @Autowired
    private EventLogMapper eventLogMapper;

    public EventLogDTO.Detail selectDetail(EventLogDTO.Param param) {
        return this.eventLogMapper.findById(param);
    }

    public List<EventLogDTO.Item> selectAll(EventLogDTO.Param param) {
        return this.eventLogMapper.findAllByCustCiNo(param);
    }

    @Transactional
    public int insert(EventLogDTO.Create create) {
        int result = 0;

        try {
            result = this.eventLogMapper.save(create);
        } catch (Exception var4) {
            log.error(var4.getMessage());
        }

        return result;
    }

}
