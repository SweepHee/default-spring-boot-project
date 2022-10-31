package com.bankpin.user.inq.controller;

import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import com.bankpin.user.inq.service.InqrsltLstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/lnqrslt")
public class InqrsltLstController
{
    @Autowired
    private InqrsltLstService inqrsltLstService;

    /**
     * 맞춤상품비교
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model)
    {
        return "lnqrslt/custom_loan_list";
    }

    /**
     * 맞춤상품비교 > 상품정보
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "lnqrslt/credit_loan_view";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/confirm-detail")
    public String confirmDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "lnqrslt/loan_Confirm";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인 > 대출 신청 접수 중
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/register-detail")
    public String registerDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "lnqrslt/bank_link_ing";
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인 > 대출 신청 접수 중 > 대출 신청 접수 중(잠시만 기다려주세요)
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/accepting-detail")
    public String acceptingDetail(InqrsltLstDTO.Param param, Model model)
    {
        model.addAttribute("lnReqNo", param.getLnReqNo());
        model.addAttribute("fintecOrgMngno", param.getFintecOrgMngno());

        return "lnqrslt/Accepting_ing";
    }

}
