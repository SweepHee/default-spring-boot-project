package com.bankpin.user.cust.service;

import com.bankpin.user.cust.mapper.CustMasMapper;
import com.bankpin.user.cust.model.dto.CustMasDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CustMastService
{
    @Autowired
    private CustMasMapper custMasMapper;

    public CustMasDTO selectCustCiNo(String custCiNo) {
        return custMasMapper.findByCustCiNo(custCiNo);
    }

    public List<CustMasDTO> selectAll() {
        return custMasMapper.findAllByCustActvGbcd(Boolean.TRUE);
    }

    public void insert() {
        // TODO
    }

    public void update() {
        // TODO
    }

    public void delete() {
        // TODO
    }

}
