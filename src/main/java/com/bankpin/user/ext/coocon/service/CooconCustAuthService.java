package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.ext.coocon.model.dto.CooconCustAuthDtlDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconCustMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CooconCustAuthService {

    private final CooconCustMapper cooconCustMapper;

    public CooconCustAuthDtlDTO.Detail findAuthDtlByCustCiNo(String custCiNo) {
        return cooconCustMapper.findAuthDtlByCustCiNo(custCiNo);
    }



    public CooconCustAuthDtlDTO.Detail findAuthDtlByCustCiNoAndLnReqNo(String custCiNo, String lnReqNo) {
        return cooconCustMapper.findAuthDtlByCustCiNoAndLnReqNo(custCiNo, lnReqNo);
    }



}
