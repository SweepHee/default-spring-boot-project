package com.bankpin.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ResponseData
{
    @Builder.Default
    private Boolean error = false;
    @Builder.Default
    private Integer code = HttpStatus.OK.value();
    private String message;
    private Object data;
}
