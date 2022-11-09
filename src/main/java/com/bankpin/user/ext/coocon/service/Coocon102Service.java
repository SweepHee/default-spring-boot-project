package com.bankpin.user.ext.coocon.service;

import com.bankpin.user.ext.coocon.model.dto.InqRsltLstDTO;
import com.bankpin.user.ext.coocon.model.mapper.CooconInqRsltLstMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Coocon102Service {

    private final CooconInqRsltLstMapper cooconInqRsltLstMapper;

    public void eachInsertIfNotExists(InqRsltLstDTO.Param param) {

        for (InqRsltLstDTO.Param.OUTLIST1 outList: param.getOutList1()) {

            InqRsltLstDTO.Create create = InqRsltLstDTO.Create.builder()
                    .lnReqNo(outList.getLoReqtNo())
                    .fintecOrgMngno(outList.getFinEnMnNo())
//                    .bankCd() // 없음
//                    .bankBrchCd() // 없음
                    .lnReqGbcd(outList.getLoPrdCd())
                    .lnPrdtCd(outList.getLoPrdCd())
                    .lnPrdtNm(outList.getLoPrdNm())
                    .lnRsltStcd(outList.getRstStCd())
                    .nrsltRsnCntn(outList.getLoNAtRsn())
                    .lstLnLmtAmt(outList.getLtLoLmAmt())
                    .lstLnRate(outList.getLtLoRt())
                    .lstLnTermMm(outList.getLoTeM())
                    .lnRtnMthCd(outList.getRpayMthdDsCd())
                    .lnRateKindGbcd(outList.getRtTpbsDsCd())
                    .strdRate(outList.getBsRt())
                    .applyRate(outList.getApRt())
                    .priRateYn(outList.getPrnlYN())
                    .priRateRsnCntn(outList.getPrnlRsn())
                    .priLmtAmt(outList.getPrnlLmAmt())
                    .priRate(outList.getPrnlRt())
                    .lnValidDttm(outList.getLoCdVDtTm())
                    .lnRateCycleCd(outList.getChgCycl())
                    .lnMidrtnFeeRate(outList.getMRpayFeRt())
                    .lnMidrtnFeeYn(outList.getMRpayFeExYN())
                    .endAllrtnAblYn(outList.getEDOFRpayAYN())
                    .imDepotAblYn(outList.getImDpAYN())
                    .holiDepotAbleYn(outList.getHdDpAYN())
                    .prinitrEqRtnAblYn(outList.getEqTotPymtYN())
                    .prinEqRtnAblYn(outList.getEqPrinPymtYN())
                    .minusBbookAblYn(outList.getOdAcYN())
                    .build();

            if (cooconInqRsltLstMapper.existsByLnReqNo(outList.getLoReqtNo())) {
                cooconInqRsltLstMapper.update(create);
            } else {
                cooconInqRsltLstMapper.save(create);
            }

        }

    }

}

