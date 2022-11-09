package com.bankpin.user.inq.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.inq.model.dto.InqMasDTO;
import com.bankpin.user.inq.service.InqMasService;
import com.bankpin.user.model.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/inq")
public class ApiInqMasController
{
    @Autowired
    private InqMasService inqMasService;

    @GetMapping("list")
    public ResponseEntity<ResponseData> apprList(@Valid InqMasDTO.Param param)
    {
        List<InqMasDTO.Item> list = inqMasService.selectAll(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(list)
                        .build());
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseData> detail(@Valid InqMasDTO.Param param, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        param.setCustCiNo(userAuth.getId());

        InqMasDTO.Detail detail = inqMasService.selectDetail(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(detail)
                        .build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData> add(@Valid @RequestBody InqMasDTO.Create create, Authentication authentication)
    {
        if (!StringUtils.hasLength(create.getStrLnInqDttm())) {
            create.setLnInqDttm(LocalDateTime.now());
        }

        if (StringUtils.hasLength(create.getStrlnHopeDt())) {
            create.setLnHopeDt(LocalDate.parse(create.getStrlnHopeDt()));
        }

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        create.setCustCiNo(userAuth.getId());

        String maxLnReqNo = inqMasService.selectMaxLnReqNo();
        create.setLnReqNo(maxLnReqNo);

        int success = inqMasService.insert(create);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(maxLnReqNo)
                        .build());
    }

    @PostMapping("/edit")
    public ResponseEntity<ResponseData> edit(@Valid @RequestBody InqMasDTO.Create edit, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        edit.setCustCiNo(userAuth.getId());

        int success = inqMasService.update(edit);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(success)
                        .build());
    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseData> remove(@Valid @RequestBody InqMasDTO.Param param, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        param.setCustCiNo(userAuth.getId());

        int success = inqMasService.delete(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(success)
                        .build());
    }

}
