package com.bankpin.user.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class EventLogDTO
{
    @Data
    @Builder
    @AllArgsConstructor
    public static class Create
    {
        private Long id;
        private String custCiNo;
        private String eventUrl;
        private String eventAction;
        private String eventIpAddr;
        private String eventDevice;
        private LocalDateTime eventDatetime;
    }

    @Data
    public static class Detail
    {
        private Long id;
        private String custCiNo;
        private String eventUrl;
        private String eventAction;
        private String eventIpAddr;
        private String eventDevice;
        private LocalDateTime eventDatetime;
    }

    @Data
    public static class Item
    {
        private Long id;
        private String custCiNo;
        private String eventUrl;
        private String eventAction;
        private String eventIpAddr;
        private String eventDevice;
        private LocalDateTime eventDatetime;
    }

    @Data
    public static class Param
    {
        private Long id;
        private String custCiNo;
        private String eventUrl;
        private String eventAction;
        private String eventIpAddr;
        private String eventDevice;
        private LocalDateTime eventDatetime;
    }

}
