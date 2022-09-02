package com.bankpin.user.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseModel
{
    private Boolean idDeleted;
    private Boolean deleted;
    private LocalDateTime deletedDate;
    private String createdId;
    private LocalDateTime createdDate;
    private String updatedId;
    private LocalDateTime updatedDate;
}
