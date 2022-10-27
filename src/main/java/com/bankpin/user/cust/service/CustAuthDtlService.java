package com.bankpin.user.cust.service;

import com.bankpin.user.cust.mapper.CustAuthDtlMapper;
import com.bankpin.user.cust.model.dto.CustAuthDtlDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustAuthDtlService
{
    private final CustAuthDtlMapper custAuthDtlMapper;

    /**
     * 조회
     * @param custCiNo
     * @param lnReqNo
     * @return
     */
    public CustAuthDtlDTO.Detail selectDetail(String custCiNo, String lnReqNo) {
        return custAuthDtlMapper.findByCustCiNoAndLnReqNo(custCiNo, lnReqNo);
    }

    /**
     * 리스트 조회
     * @param custCiNo
     * @return
     */
    public List<CustAuthDtlDTO.Item> selectAllCustCiNo(String custCiNo) {
        return custAuthDtlMapper.findAllByCustCiNo(custCiNo);
    }

    /**
     * 등록
     * @param custAuthDtlDTO
     * @return
     */
    @Transactional
    public int insertCustAuthDtlDTO(CustAuthDtlDTO.Create custAuthDtlDTO) {
        return custAuthDtlMapper.save(custAuthDtlDTO);
    }

    /**
     * 수정
     * @param custAuthDtlDTO
     * @return
     */
    @Transactional
    public int updateCustAuthDtlDTO(CustAuthDtlDTO.Create custAuthDtlDTO) {
        return custAuthDtlMapper.update(custAuthDtlDTO);
    }

}
