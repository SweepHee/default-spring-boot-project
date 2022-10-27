package com.bankpin.user.mrtg.service;

import com.bankpin.user.mrtg.mapper.MrtgMasMapper;
import com.bankpin.user.mrtg.model.dto.MrtgMasDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MrtgMasService
{
    @Autowired
    private MrtgMasMapper mrtgMasMapper;

    public MrtgMasDTO.Detail selectDetail(String lnMrtgNo) {
        return mrtgMasMapper.findByLnMrtgNo(lnMrtgNo);
    }

    public List<MrtgMasDTO.Item> selectAll(String mrtgKindCd) {
        return mrtgMasMapper.findAll(mrtgKindCd);
    }

    @Transactional
    public int insert(MrtgMasDTO.Create create) {
        return mrtgMasMapper.save(create);
    }

    @Transactional
    public int update(MrtgMasDTO.Create create) {
        return mrtgMasMapper.edit(create);
    }

    @Transactional
    public int delete(MrtgMasDTO.Param param) {
        return mrtgMasMapper.remove(param);
    }

}
