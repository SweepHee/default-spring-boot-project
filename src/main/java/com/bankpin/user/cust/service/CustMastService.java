package com.bankpin.user.cust.service;

import com.bankpin.user.cust.mapper.CustMasMapper;
import com.bankpin.user.cust.model.dto.CustMasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustMastService
{
    @Autowired
    private CustMasMapper custMasMapper;

    public CustMasDTO selectCustCiNo(String custCiNo) {
        return custMasMapper.findByCustCiNo(custCiNo);
    }

}
