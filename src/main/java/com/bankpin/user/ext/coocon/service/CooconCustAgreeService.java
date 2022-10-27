package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.ext.coocon.model.dto.CooconCustAgreeLstDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconCustAgreeLstMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CooconCustAgreeService {

    private final CooconCustAgreeLstMapper cooconCustAgreeLstMapper;

    public List<CooconCustAgreeLstDTO.Create> findByCustCiNo(String custciNo) {
        return cooconCustAgreeLstMapper.findByCustCiNo(custciNo);
    }

}
