package com.bankpin.user.inq.controller;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import com.bankpin.user.inq.model.type.LnGbcdType;
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

    /**
     * 맞춤상품비교
     * @param param
     * @param authentication
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseData> list(@Valid InqrsltLstDTO.Param param, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        param.setCustCiNo(userAuth.getId());

        param.setLnGbcd(LnGbcdType.CREDIT.getValue());  // 신용
        List<InqrsltLstDTO.Item> list = inqrsltLstService.selectAll(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(list)
                        .build());
    }

    /**
     * 맞춤상품비교 > 상품정보
     * @param param
     * @param authentication
     * @return
     */
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

    /**
     * 맞춤상품비교 > 진행중, 완료, 검색결과
     * @param param
     * @param authentication
     * @return
     */
    @GetMapping("/progress-detail")
    public ResponseEntity<ResponseData> progressDetail(@Valid InqrsltLstDTO.Param param, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        param.setCustCiNo(userAuth.getId());

        InqrsltLstDTO.Progress item = InqrsltLstDTO.Progress.builder()
                .progress(12)
                .done(12)
                .result(0)
                .build();

        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(item)
                        .build());
    }

    /**
     * 맞춤상품비교 > 상품정보 > 대출신청 정보확인
     * @param param
     * @param authentication
     * @return
     */
    @GetMapping("/confirm-detail")
    public ResponseEntity<ResponseData> loanDetail(@Valid InqrsltLstDTO.Param param, Authentication authentication)
    {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        param.setCustCiNo(userAuth.getId());

        InqrsltLstDTO.Detail detail = inqrsltLstService.selectDetail(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(detail)
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
