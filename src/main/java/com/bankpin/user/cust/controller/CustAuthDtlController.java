package com.bankpin.user.cust.controller;

import com.bankpin.user.cust.model.dto.CustAuthDtlDTO;
import com.bankpin.user.cust.service.CustAuthDtlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/cust")
public class CustAuthDtlController
{
    @Autowired
    private CustAuthDtlService custAuthDtlService;

    @GetMapping("/list")
    public String list(CustAuthDtlDTO.Param param) {
        custAuthDtlService.selectAllCustCiNo(param.getCustCiNo());
        return "cust/list";
    }

    @GetMapping("/detail")
    public String detail(CustAuthDtlDTO.Param param, Model model) {
        CustAuthDtlDTO.Detail detail = custAuthDtlService.selectDetail(param.getCustCiNo(), param.getLnReqNo());
        return "cust/detail";
    }

    @PostMapping("/add")
    public String add() {
        return "cust/detail";
    }

    @PostMapping("/edit")
    public String edit() {
        return "cust/detail";
    }

    @PostMapping("/remove")
    public String remove() {
        return "redirect:/user/cust/list";
    }

}
