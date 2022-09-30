package com.bankpin.user.mrtg.controller;

import com.bankpin.user.mrtg.model.dto.MrtgMasDTO;
import com.bankpin.user.mrtg.service.MrtgMasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/mrtg")
public class MrtgMasController
{
    @Autowired
    private MrtgMasService mrtgMasService;

    @GetMapping("/list")
    public String list(String mrtgKindCd, Model model) {
        List<MrtgMasDTO.Item> list = mrtgMasService.selectAll(mrtgKindCd);
        model.addAttribute("list", list);
        return "mrtg/list";
    }

    @GetMapping("/detail")
    public String detail(String lnMrtgNo, Model model) {
        MrtgMasDTO.Detail detail = mrtgMasService.selectDetail(lnMrtgNo);
        model.addAttribute("detail", detail);
        return "mrtg/detail";
    }

    public String add(MrtgMasDTO.Create create, Model model) {
        int success = mrtgMasService.insert(create);
        model.addAttribute("success", success);
        return "mrtg/detail";
    }

    public String edit(MrtgMasDTO.Create edit, Model model) {
        int success = mrtgMasService.update(edit);
        model.addAttribute("success", success);
        return "mrtg/detail";
    }

    public String remove(MrtgMasDTO.Param param, Model model) {
        int success = mrtgMasService.delete(param);
        model.addAttribute("success", success);
        return "redirect:/user/mrtg/list";
    }

}
