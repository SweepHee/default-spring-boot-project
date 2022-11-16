package com.bankpin.user.ext.coocon.service;
import com.bankpin.user.ext.coocon.model.dto.CooconLogDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CooconLogService {

    private final CooconLogMapper cooconLogMapper;

    public void create(CooconLogDTO.Create create) {
        cooconLogMapper.save(create);
    }

    public void update(CooconLogDTO.Create create) {
        cooconLogMapper.update(create);
    }

}
