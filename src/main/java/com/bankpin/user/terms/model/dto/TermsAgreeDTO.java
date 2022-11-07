package com.bankpin.user.terms.model.dto;

import com.bankpin.user.terms.model.type.TermsType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class TermsAgreeDTO {

    @Builder
    @Data
    public static class Create {

        private String userId;
        private String termsType;
        private String accept;
        private String createdDate;
        private String updatedDate;

    }

    @Builder
    @Data
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Param {

        private String userId;

        @Valid
        private List<TermsAgree> termsTypes;

        @Builder
        @Data
        @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class TermsAgree {

            private TermsType termsType;

            @NotNull
            @Pattern(regexp = "^[Yy]$", message = "동의하지 않았습니다")
            private String accept;

        }

    }

}