package com.bankpin.user.inq.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import com.bankpin.user.inq.service.InqrsltLstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/inqrslt")
public class InqrsltLstController
{
    @Autowired
    private InqrsltLstService inqrsltLstService;

    /**
     * 맞춤상품비교
     */
    @GetMapping("/list")
    public String list(Model model, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        model.addAttribute("name", userAuth.getName());

        return "inqrslt/custom_loan_list";
    }

    /**
     * 맞춤상품비교 > 상품정보
     */
    @GetMapping("/detail")
    public String detail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "inqrslt/credit_loan_view";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인
     */
    @GetMapping("/confirm-detail")
    public String confirmDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());
        model.addAttribute("specialRate", param.getSpecialRate());  // 거래실적 우대금리

        return "inqrslt/loan_Confirm";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인 > 금융사 링크
     */
    @GetMapping("/register-detail")
    public String registerDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "inqrslt/bank_link_ing";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인 > 금융사 링크 > 접수중(잠시만 기다려주세요)
     */
    @GetMapping("/accepting-detail")
    public String acceptingDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "inqrslt/Accepting_ing";
    }

    /**
     * 검색중
     */
    @GetMapping("/searching-detail")
    public String searchingDetail(Model model, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        model.addAttribute("name", userAuth.getName());

        int total = inqrsltLstService.selectCountCustCiNo(
                InqrsltLstDTO.Param.builder()
                        .custCiNo(userAuth.getId())
                        .build());

        model.addAttribute("total", total);

        return "inqrslt/search_ing";
    }


    /**
     * 대출 신청이 접수되었습니다.
     */
    @GetMapping("/search-complete")
    public String searchComplete()
    {
        return "inqrslt/app_com02";
    }

}
