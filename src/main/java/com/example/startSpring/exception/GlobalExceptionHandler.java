package com.example.startSpring.exception;

import com.example.startSpring.dto.request.ApiResponseConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponseConfig> handingRuntimeException(RuntimeException exception){
        ApiResponseConfig apiResponseConfig = new ApiResponseConfig();
        apiResponseConfig.setCode(400);
        apiResponseConfig.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponseConfig);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponseConfig> handingRuntimeException(AppException exception){
        ErrorCode errorCode =  exception.getErrorCode();
        ApiResponseConfig apiResponseConfig = new ApiResponseConfig();
        apiResponseConfig.setCode(errorCode.getCode());
        apiResponseConfig.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponseConfig);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponseConfig> handingValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
             errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }

        ApiResponseConfig apiResponseConfig = new ApiResponseConfig();
        apiResponseConfig.setCode(errorCode.getCode());
        apiResponseConfig.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponseConfig);
    }
}
