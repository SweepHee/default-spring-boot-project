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
//        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
//        model.addAttribute("name", userAuth.getName());
        model.addAttribute("name", "고객님");

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

        return "inqrslt/loan_Confirm";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인 > 대출 신청 접수 중
     */
    @GetMapping("/register-detail")
    public String registerDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "inqrslt/bank_link_ing";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인 > 대출 신청 접수 중 > 대출 신청 접수 중(잠시만 기다려주세요)
     */
    @GetMapping("/accepting-detail")
    public String acceptingDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "inqrslt/Accepting_ing";
    }

}
