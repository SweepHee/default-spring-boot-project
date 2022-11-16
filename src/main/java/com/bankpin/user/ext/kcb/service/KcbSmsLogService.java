package com.bankpin.user.ext.kcb.service;

import com.bankpin.user.ext.kcb.model.dto.KcbSmsLogDTO;
import com.bankpin.user.ext.kcb.model.dto.SmsDTO;
import com.bankpin.user.ext.kcb.model.mapper.KcbSmsLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KcbSmsLogService {

    private final KcbSmsLogMapper kcbSmsLogMapper;


    public Long create(KcbSmsLogDTO.Create create) {
        return kcbSmsLogMapper.save(create);
    }

    public void update(KcbSmsLogDTO.Create logDto) {
        kcbSmsLogMapper.update(logDto);
    }
}
