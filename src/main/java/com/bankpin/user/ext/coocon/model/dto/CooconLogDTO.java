package com.bankpin.user.ext.coocon.model.dto;

import lombok.Builder;
import lombok.Data;

public class CooconLogDTO {

    @Data
    @Builder
    public static class Create {

        private Long id;
        private String custCiNo;
        private String apiUrl;
        private String apiType;
        private String apiIpAddr;
        private String apiCntn;
        private String apiOutCntn;
        private String apiDatetime;

    }

}
