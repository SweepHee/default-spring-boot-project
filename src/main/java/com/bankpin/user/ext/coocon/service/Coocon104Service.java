package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.ext.coocon.model.dto.Coocon104DTO;
import com.bankpin.user.ext.coocon.model.dto.ExecInfoDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconExecInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Coocon104Service {

    private final CooconExecInfoMapper cooconExecInfoMapper;

    public void save(ExecInfoDTO.RequestParams param) {
        ExecInfoDTO.Create create = ExecInfoDTO.Create.builder()
                .lnReqNo(param.getLoAplcMmNo())
                .fintecOrgMngno(param.getAlncIsMnNo())
                .lnReqYn("Y") // 대출신청여부. 103을 보내면 하는 거니까 했다고 판단
                .lnReqDttm(param.getAplyDt())
//                .bankCd()  // 은행코드 없음
//                .bankBrchCd() // 은행지점코드
                .lnPrdtCd(param.getLoPrdCd())
//                .lnPrdtNm() // 상품명 없음
                .lnAmt(param.getLoReqAmt())
                .lstLnLmtAmt(param.getLtLoLmAmt())
                .lnRateKindGbcd(param.getLtLoRt())
                .strdRate(param.getBsRt())
                .applyRate(param.getApRt())
                .priRateRsnCntn(param.getPrnlRsn())
                .priLmtAmt(param.getPrnlLmAmt())
                .priRate(param.getPrnlRt())
                .lnReqDt(param.getAplyDt())
//                .lnRateCycleCd() // 대출금리주기코드. 알수없음
                .lnAuthDt(param.getLoCtDt())
                .build();

        cooconExecInfoMapper.save(create);

    }

    public void upsert(Coocon104DTO.Param param) {

        ExecInfoDTO.Create create = ExecInfoDTO.Create.builder()
                .lnReqNo(param.getLoAplcMmNo())
                .fintecOrgMngno(param.getAlncIsMnNo())
                .lnReqYn("Y")
                .lnReqDttm(param.getAplyDt())
//                .bankCd()  // 은행코드 없음
//                .bankBrchCd() // 은행지점코드
                .lnPrdtCd(param.getLoPrdCd())
//                .lnPrdtNm() // 상품명 없음
                .lnAmt(param.getLoReqAmt())
                .lstLnLmtAmt(param.getLtLoLmAmt())
                .lnRateKindGbcd(param.getLtLoRt())
                .strdRate(param.getBsRt())
                .applyRate(param.getApRt())
                .priRateRsnCntn(param.getPrnlRsn())
                .priLmtAmt(param.getPrnlLmAmt())
                .priRate(param.getPrnlRt())
                .lnReqDt(param.getAplyDt())
//                .lnRateCycleCd() // 대출금리주기코드. 알수없음
                .lnAuthDt(param.getLoCtDt())
                .build();

        if (cooconExecInfoMapper.existsByLnReqNoAndFintecOrgMngnoAndLnPrdtCd(create)) {
            cooconExecInfoMapper.update(create);
        } else {
            cooconExecInfoMapper.save(create);
        }

    }
}
