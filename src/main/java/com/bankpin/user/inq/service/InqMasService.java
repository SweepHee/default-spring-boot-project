package com.bankpin.user.inq.service;

import com.bankpin.user.inq.mapper.InqMasMapper;
import com.bankpin.user.inq.model.dto.InqMasDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class InqMasService
{
    @Autowired
    private InqMasMapper inqMasMapper;

    public InqMasDTO.Detail selectDetail(InqMasDTO.Param param) {
        return inqMasMapper.findByCustCiNo(param);
    }

    public List<InqMasDTO.Item> selectAll(InqMasDTO.Param param) {
        return inqMasMapper.findAll(param);
    }

    public String selectMaxLnReqNo() {
        return inqMasMapper.findByMaxLnReqNo();
    }

    @Transactional
    public int insert(InqMasDTO.Create create) {
        return inqMasMapper.save(create);
    }

    @Transactional
    public int update(InqMasDTO.Create edit) {
        return inqMasMapper.edit(edit);
    }

    @Transactional
    public int delete(InqMasDTO.Param param) {
        return inqMasMapper.remove(param);
    }

}
