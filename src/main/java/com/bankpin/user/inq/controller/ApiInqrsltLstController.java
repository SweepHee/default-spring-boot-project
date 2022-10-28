package com.bankpin.user.inq.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import com.bankpin.user.inq.service.InqrsltLstService;
import com.bankpin.user.model.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/inqrslt")
public class ApiInqrsltLstController
{
    @Autowired
    private InqrsltLstService inqrsltLstService;

    @GetMapping("/list")
    public ResponseEntity<ResponseData> list(@Valid InqrsltLstDTO.Param param, Authentication authentication)
    {
//        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
//        param.setCustCiNo(userAuth.getId());
        param.setCustCiNo("f2633a08330511ed8b3a0242ac130003");

        List<InqrsltLstDTO.Item> list = inqrsltLstService.selectAll(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(list)
                        .build());
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseData> detail(@Valid InqrsltLstDTO.Param param, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        param.setCustCiNo(userAuth.getId());

        InqrsltLstDTO.Detail detail = inqrsltLstService.selectDetail(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(detail)
                        .build());
    }

    @GetMapping("/progress-detail")
    public ResponseEntity<ResponseData> progressDetail(Authentication authentication)
    {
//        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        InqrsltLstDTO.Progress item = InqrsltLstDTO.Progress.builder()
                .progress(12)
                .done(12)
                .result(0)
//                .custNm(userAuth.getName())
                .custNm("고객님")
                .build();

        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(item)
                        .build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData> add(@Valid @RequestBody InqrsltLstDTO.Create create)
    {
        int success = inqrsltLstService.insert(create);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(success)
                        .build());
    }

    @PostMapping("/edit")
    public ResponseEntity<ResponseData> edit(@Valid @RequestBody InqrsltLstDTO.Create edit)
    {
        int success = inqrsltLstService.update(edit);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(success)
                        .build());
    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseData> remove(@Valid @RequestBody InqrsltLstDTO.Param param)
    {
        int success = inqrsltLstService.delete(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(success)
                        .build());
    }

}
