package com.bankpin.user.controller;

import com.bankpin.user.inq.model.dto.InqrsltLstDTO;
import com.bankpin.user.inq.model.type.LnGbcdType;
import com.bankpin.user.inq.model.type.LnRsltStcdType;
import com.bankpin.user.model.dto.MainDTO;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/main")
public class ApiMainController
{
    @Autowired
    private MainService mainService;

    /**
     * 메인 빠른 대출 승인
     * @return 대출 승인 리스트
     */
    @GetMapping("/appr-list")
    public ResponseEntity<ResponseData> apprList()
    {
        List<MainDTO.FastItem> items = mainService.selectListSpeedLoan(
                MainDTO.Param.builder()
                        .lnGbcd(LnGbcdType.CREDIT.getValue())
                        .lnRsltStcd(LnRsltStcdType.ACCEPT.getValue())
                        .build());

        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(items)
                        .build());
    }

    @GetMapping("/my-credit-score")
    public ResponseEntity<ResponseData> myCreditScore()
    {
        /*
            <p class="view_more">
                    <strong>내 신용점수</strong>
                    <em>2022.08.22 기준 / 1000점 만점</em>
                <!--조회 후 다시 조회할 때 텍스트 변경-->
                <button>다시 조회하기</button>

            <!--조회 후 신용점수 영역-->
            <div class="score">
                <strong><b>985</b>점</strong>
                    <span>상위 <b>2%</b></span>
                    <span>연체 가능 <b>0.09%</b></span>
                <div class="progress-bar">
                    <div class="progress"></div>
                </div>
            </div>
        */
        Map<String, Object> item = new HashMap<>();

        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(item)
                        .build());
    }

    @GetMapping("/loan-total")
    public ResponseEntity<ResponseData> loanTotal()
    {
        // 총 원금, 총 잔액
        Map<String, Object> item = new HashMap<>();

        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(item)
                        .build());
    }

    @GetMapping("/loan-list")
    public ResponseEntity<ResponseData> loanList()
    {
        /*
            <img src="/user/static/assets/images/common/bank01.svg">
            <p class="bank">
                <span>한국씨티은행</span>
                <em>살만한 신용대출</em>
            <p class="pay">
                <em>16.3%</em>
                <span><b>5,000</b>만원</span>
        */

        List<Map<String, Object>> items = new ArrayList<>();

        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(items)
                        .build());
    }

    @GetMapping("/rate-info-list")
    public ResponseEntity<ResponseData> rateInfoList(InqrsltLstDTO.Param param, Authentication authentication)
    {
        param.setLnGbcd(LnGbcdType.CREDIT.getValue());  // 신용
        List<InqrsltLstDTO.Item> list = mainService.selectAll(param);
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(list)
                        .build());
    }

}
