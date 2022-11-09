package com.bankpin.user.ext.kcb.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;


public interface PropertyConfig {

    public String getPath();
    public String getCpCd();
    public String getTarget();

    public PropertyConfig getInstance();
    
}