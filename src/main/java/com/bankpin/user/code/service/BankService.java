package com.bankpin.user.code.service;

import com.bankpin.user.code.mapper.BankMapper;
import com.bankpin.user.code.model.dto.BankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService
{
    @Autowired
    private BankMapper bankMapper;

    public List<BankDTO.Item> selectAll(BankDTO.Param param) {
        return bankMapper.findAll(param);
    }

    public BankDTO.Detail selectDetail(BankDTO.Param param) {
        return bankMapper.findByBankCd(param);
    }

}
