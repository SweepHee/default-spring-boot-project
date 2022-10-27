package com.bankpin.user.config.interceptor;

import com.bankpin.user.auth.model.dto.UserAuth;
import com.bankpin.user.common.model.dto.EventLogDTO;
import com.bankpin.user.common.service.EventLogService;
import com.bankpin.user.model.type.BankpinType;
import com.bankpin.user.common.model.type.DeviceType;
import com.bankpin.user.common.model.type.EventActionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor
{
    @Autowired
    private EventLogService eventLogService;

    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String userId = BankpinType.SIGNOUT_ID.getValue();
        String uri = request.getRequestURI();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserAuth) {
                    UserAuth userAuth = (UserAuth)principal;
                    userId = userAuth.getId();
                }
            }
        } catch (Exception var10) {
            log.error(var10.getMessage());
        }

        Device device = DeviceUtils.getCurrentDevice(request);
        try {
            this.eventLogService.insert(
                    EventLogDTO.Create.builder()
                            .custCiNo(userId)
                            .eventUrl(uri)
                            .eventAction(eventActionType(uri))
                            .eventIpAddr(request.getRemoteAddr())
                            .eventDevice(currentDeviceType(device))
                            .build());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private String eventActionType(String txt)
    {
        String type = EventActionType.READ.getValue();
        try {
            if (txt.endsWith("detail")) {
                type = EventActionType.READ.getValue();
            } else if (txt.endsWith("list")) {
                type = EventActionType.READ.getValue();
            } else if (txt.endsWith("add")) {
                type = EventActionType.ADD.getValue();
            } else if (txt.endsWith("edit")) {
                type = EventActionType.EDIT.getValue();
            } else if (txt.endsWith("remove")) {
                type = EventActionType.REMOVE.getValue();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return type;
    }

    private String currentDeviceType(Device device)
    {
        String userDevice = DeviceType.DESKTOP.getValue();
        if (device.isMobile()) {
            userDevice = DeviceType.MOBILE.getValue();
        } else if (device.isTablet()) {
            userDevice = DeviceType.TABLET.getValue();
        }
        return userDevice;
    }

}
