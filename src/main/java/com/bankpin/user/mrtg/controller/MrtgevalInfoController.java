package com.bankpin.user.mrtg.controller;

import com.bankpin.user.mrtg.model.dto.MrtgevalInfoDTO;
import com.bankpin.user.mrtg.service.MrtgevalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mrtgeval")
public class MrtgevalInfoController
{
    @Autowired
    private MrtgevalInfoService mrtgevalInfoService;

    @GetMapping("/list")
    public String list(MrtgevalInfoDTO.Param param, Model model) {
        List<MrtgevalInfoDTO.Item> list = mrtgevalInfoService.selectAll(param.getLnMrtgNo());
        model.addAttribute("list", list);
        return "mrtgeval/list";
    }

    @GetMapping("/detail")
    public String detail(MrtgevalInfoDTO.Param param, Model model) {
        MrtgevalInfoDTO.Detail detail = mrtgevalInfoService.selectDetail(param.getLnMrtgNo(), param.getMrtgSeqNo());
        model.addAttribute("detail", detail);
        return "mrtgeval/detail";
    }

    @PostMapping("/add")
    public String add(MrtgevalInfoDTO.Create create, Model model) {
        int success = mrtgevalInfoService.insert(create);
        model.addAttribute("success", success);
        return "mrtgeval/detail";
    }

    @PostMapping("/edit")
    public String edit(MrtgevalInfoDTO.Create edit, Model model) {
        int success = mrtgevalInfoService.update(edit);
        model.addAttribute("success", success);
        return "mrtgeval/detail";
    }

    @PostMapping("/remove")
    public String remove(MrtgevalInfoDTO.Param param, Model model) {
        int success = mrtgevalInfoService.delete(param);
        model.addAttribute("success", success);
        return "redirect:/user/mrtgeval/list";
    }

}
