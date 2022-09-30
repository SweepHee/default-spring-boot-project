package com.bankpin.user.inq.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.inq.model.dto.InqMasDTO;
import com.bankpin.user.inq.service.InqMasService;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/inq")
public class ApiInqMasController
{
    @Autowired
    private InqMasService inqMasService;

    @GetMapping("list")
    public ResponseEntity<ResponseData> apprList(@RequestBody InqMasDTO.Param param)
    {
        List<InqMasDTO.Item> list = null;
        try {
            list = inqMasService.selectAll(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(
                ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .build());
        }
        return ResponseEntity.ok(
                ResponseData.builder()
                    .data(list)
                    .build());
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseData> detail(@RequestBody InqMasDTO.Param param, Authentication authentication)
    {
        InqMasDTO.Detail detail = null;
        try {
            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            param.setCustCiNo(userAuth.getId());

            detail = inqMasService.selectDetail(param);
        } catch (Exception e) {
            return ResponseEntity.ok(
                ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .message(e.getMessage())
                    .build());
        }
        return ResponseEntity.ok(
                ResponseData.builder()
                    .data(detail)
                    .build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData> add(@Validated @RequestBody InqMasDTO.Create create, BindingResult bindingResult, Authentication authentication)
    {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            log.error(errors.toString());
            return ResponseEntity.ok(
                ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .data(errors)
                    .build());
        }

        if (!StringUtils.hasLength(create.getStrLnInqDttm())) {
            create.setLnInqDttm(LocalDateTime.now());
        }

        if (StringUtils.hasLength(create.getStrlnHopeDt())) {
            create.setLnHopeDt(LocalDate.parse(create.getStrlnHopeDt()));
        }

        int success = 0;
        try {
            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            create.setCustCiNo(userAuth.getId());

            String maxLnReqNo = inqMasService.selectMaxLnReqNo();
            create.setLnReqNo(maxLnReqNo);

            success = inqMasService.insert(create);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(
                ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .build());
        }
        return ResponseEntity.ok(
                ResponseData.builder()
                    .data(success)
                    .build());
    }

    @PostMapping("/edit")
    public ResponseEntity<ResponseData> edit(@RequestBody InqMasDTO.Create edit, Authentication authentication)
    {
        int success = 0;
        try {
            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            edit.setCustCiNo(userAuth.getId());

            success = inqMasService.update(edit);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(
                ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .build());
        }
        return ResponseEntity.ok(
                ResponseData.builder()
                    .data(success)
                    .build());
    }

    @PostMapping("/remove")
    public ResponseEntity<ResponseData> remove(@RequestBody InqMasDTO.Param param, Authentication authentication)
    {
        int success = 0;
        try {
            UserAuth userAuth = (UserAuth) authentication.getPrincipal();
            param.setCustCiNo(userAuth.getId());

            success = inqMasService.delete(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(
                ResponseData.builder()
                    .error(true)
                    .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                    .build());
        }
        return ResponseEntity.ok(
                ResponseData.builder()
                    .data(success)
                    .build());
    }

}
