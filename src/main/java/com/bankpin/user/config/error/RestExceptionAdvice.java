package com.bankpin.user.config.error;

import com.bankpin.user.model.dto.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.bankpin.user.inq.controller")
public class RestExceptionAdvice
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData> handleValidationExceptions(MethodArgumentNotValidException manve)
    {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(true)
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .data(errors)
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseData> handleException(Exception e)
    {
        log.error(e.getMessage());
        return ResponseEntity.ok(
                ResponseData.builder()
                        .error(true)
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .build());
    }

}
