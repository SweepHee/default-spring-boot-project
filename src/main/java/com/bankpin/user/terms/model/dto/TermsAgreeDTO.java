package com.bankpin.user.terms.model.dto;

import com.bankpin.user.terms.model.type.TermsType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

public class TermsAgreeDTO {

    @Builder
    @Data
    public static class Create {

        private String userId;
        private String lnReqNo;
        private String termsType;
        private String accept;
        private int requirement;
        private String createdDate;
        private String updatedDate;

    }

    @Builder
    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Param {

        private String userId;

        @NotNull(message = "일련번호는 필수입니다")
        private String lnReqNo;

        @Valid
        private Set<TermsAgree> termsTypes;

        @Builder
        @Data
        @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class TermsAgree {

            private TermsType termsType;
            private boolean requirement;

            @NotNull
            @Pattern(regexp = "^[YyNn]$", message = "동의하지 않았습니다")
            private String accept;

        }

    }

}