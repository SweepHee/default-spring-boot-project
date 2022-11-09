package com.bankpin.user.inq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/inq")
public class InqMasController
{
    @GetMapping(value = {"", "/"})
    public String inq()
    {
        return "redirect:/main";
    }

}
