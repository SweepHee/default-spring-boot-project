package com.bankpin.user.inq.service;

import com.bankpin.user.inq.mapper.InqrsltLstMapper;
import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class InqrsltLstService
{
    @Autowired
    private InqrsltLstMapper inqrsltLstMapper;

    public int selectCountCustCiNo(InqrsltLstDTO.Param param) {
        return inqrsltLstMapper.countByCustCiNo(param);
    }

    public InqrsltLstDTO.Detail selectDetail(InqrsltLstDTO.Param param) {
        return inqrsltLstMapper.findByLnReqNoAndFintecOrgMngno(param);
    }

    public List<InqrsltLstDTO.Item> selectAll(InqrsltLstDTO.Param param) {
        return inqrsltLstMapper.findAllByLnReqNo(param);
    }

    @Transactional
    public int insert(InqrsltLstDTO.Create create) {
        return inqrsltLstMapper.save(create);
    }

    @Transactional
    public int update(InqrsltLstDTO.Create edit) {
        return inqrsltLstMapper.edit(edit);
    }

    @Transactional
    public int delete(InqrsltLstDTO.Param param) {
        return inqrsltLstMapper.remove(param);
    }

}
