package com.bankpin.user.ext.coocon.receive.controller;

import com.bankpin.user.ext.coocon.model.dto.*;
import com.bankpin.user.ext.coocon.service.Coocon102Service;
import com.bankpin.user.ext.coocon.service.Coocon104Service;
import com.bankpin.user.ext.coocon.service.CooconLogService;
import com.bankpin.user.model.dto.ResponseData;
import com.bankpin.user.model.type.HttpCodeType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kcb.org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/coocon/callback")
@RequiredArgsConstructor
public class ApiReceiveController {

    private final Coocon102Service coocon102Service;
    private final Coocon104Service coocon104Service;
    private final CooconLogService cooconLogService;
    private final ObjectMapper objectMapper;


    /**
     * 대출금리한도 조회
     * */
    @PostMapping("/102")
    public ResponseEntity<ResponseData> response102(@RequestBody @Valid Coocon102DTO.Param param)
            throws JsonProcessingException {

        JSONObject test = new JSONObject(param);
        System.out.println(test);

        try {

            coocon102Service.eachInsertIfNotExists(param);

        } catch (Exception e) {

            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(e.getMessage())
                            .build());

        }

        Coocon102DTO.Output output = Coocon102DTO.Output.builder()
                .Common(param.getCommon())
                .fillerI(null)
                .build();

        CooconLogDTO.Create logDto = CooconLogDTO.Create.builder()
                .id(param.getLogId())
                .apiOutput(objectMapper.writeValueAsString(output))
                .build();
        cooconLogService.update(logDto);


        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .data(output)
                        .build());
    }


    /**
    * 대출실행결과통지 ADL_104_IF
    * */
    @PostMapping("/104")
    public ResponseEntity<ResponseData> response104(@RequestBody Coocon104DTO.Param param) throws JsonProcessingException {

        try {

            coocon104Service.upsert(param);

        } catch (Exception e) {

            return ResponseEntity.ok(
                    ResponseData.builder()
                            .error(true)
                            .code(HttpCodeType.INTERNAL_SERVER_ERROR.getCode())
                            .message(e.getMessage())
                            .build());

        }

        Coocon104DTO.Output output = Coocon104DTO.Output.builder()
                .Common(param.getCommon())
                .loAplcMmNo(param.getLoAplcMmNo())
                .alncIsMnNo(param.getAlncIsMnNo())
                .loPrdCd(param.getLoPrdCd())
                .blank1(param.getBlank1())
                .fillerI(param.getFillerI())
                .build();

        CooconLogDTO.Create logDto = CooconLogDTO.Create.builder()
                .id(param.getLogId())
                .apiOutput(objectMapper.writeValueAsString(output))
                .build();
        cooconLogService.update(logDto);

        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(false)
                        .code(HttpCodeType.OK.getCode())
                        .message("")
                        .data(output)
                        .build());

    }

}





