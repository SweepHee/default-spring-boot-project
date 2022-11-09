package com.bankpin.user.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
