package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.ext.coocon.model.dto.InqRsltLstDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconInqRsltLstMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CooconInqRsltLstService {


    private final CooconInqRsltLstMapper cooconInqRsltLstMapper;



    public InqRsltLstDTO.Create findByLnReqNo(String lnReqNo) {
        return cooconInqRsltLstMapper.findByLnReqNo(lnReqNo);
    }


    public InqRsltLstDTO.Create findByLnReqNoAndFinEnMnNoAndLoPrdCd(String lnReqNo, String finEnMnNo, String loPrdCd) {
        return cooconInqRsltLstMapper.findByLnReqNoAndFinEnMnNoAndLoPrdCd(lnReqNo, finEnMnNo, loPrdCd);
    }

}
