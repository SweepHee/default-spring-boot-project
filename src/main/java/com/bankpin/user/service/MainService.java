package com.bankpin.user.service;

import com.bankpin.user.model.dto.MainDTO;
import com.bankpin.user.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService
{
    @Autowired
    private MainMapper mainMapper;

    /**
     * 메인 > 빠른 대출 승인 리스트
     * @param param 대출 승인 조건
     * @return 대출 승인 리스트
     */
    public List<MainDTO.FastItem> selectListSpeedLoan(MainDTO.Param param)
    {
        return mainMapper.findByLnReqGbcd(param);
    }

    public List<MainDTO.RateInfo> selectAllRateInfoList(MainDTO.Param param) {
        return mainMapper.findAllGroupByBankCd(param);
    }

    public MainDTO.RateSummary selectRateSummary(MainDTO.Param param) {
        return mainMapper.findRageSummary(param);
    }

}
