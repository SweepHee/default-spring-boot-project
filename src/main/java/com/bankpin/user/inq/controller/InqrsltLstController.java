package com.bankpin.user.inq.controller;

import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import com.bankpin.user.inq.service.InqrsltLstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/lnqrslt")
public class InqrsltLstController
{
    @Autowired
    private InqrsltLstService inqrsltLstService;

    @GetMapping("/list")
    public String list(Model model)
    {
        return "lnqrslt/custom_loan_list";
    }

    @GetMapping("/detail")
    public String detail(InqrsltLstDTO.Param param)
    {
        return "lnqrslt/credit_loan_view";
    }

}
