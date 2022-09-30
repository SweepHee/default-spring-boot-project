package com.bankpin.user.model.dto;

import com.bankpin.user.model.type.HttpCodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseData
{
    @Builder.Default
    private Boolean error = false;
    @Builder.Default
    private Integer code = HttpCodeType.OK.getCode();
    private String message;
    private Object data;
}
