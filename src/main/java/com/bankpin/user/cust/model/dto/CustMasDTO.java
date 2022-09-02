package com.bankpin.user.cust.model.dto;

import com.bankpin.user.model.dto.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class CustMasDTO
{
    @Getter
    @Setter
    public static class Create
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custNm;
    }


    @Getter
    @Setter
    public static class Detail extends BaseModel
    {
        private String custCiNo;
        private String custSimplePwd;
        private String custNm;
    }


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Item extends BaseModel
    {
        private String custCiNo;
        private String custNm;
    }

}
