package com.bankpin.user.mrtg.service;

import com.bankpin.user.mrtg.mapper.MrtgevalInfoMapper;
import com.bankpin.user.mrtg.model.dto.MrtgevalInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MrtgevalInfoService
{
    @Autowired
    private MrtgevalInfoMapper mrtgevalInfoMapper;

    public MrtgevalInfoDTO.Detail selectDetail(String lnMrtgNo, int mrtgSeqNo) {
        return mrtgevalInfoMapper.findByLnMrtgNo(lnMrtgNo, mrtgSeqNo);
    }

    public List<MrtgevalInfoDTO.Item> selectAll(String lnMrtgNo) {
        return mrtgevalInfoMapper.findAll(lnMrtgNo);
    }

    @Transactional
    public int insert(MrtgevalInfoDTO.Create create) {
        return mrtgevalInfoMapper.save(create);
    }

    @Transactional
    public int update(MrtgevalInfoDTO.Create edit) {
        return mrtgevalInfoMapper.edit(edit);
    }

    @Transactional
    public int delete(MrtgevalInfoDTO.Param param) {
        return mrtgevalInfoMapper.remove(param);
    }

}
