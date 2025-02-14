package com.z.admin.config;

import com.z.admin.entity.enums.ResultCodeEnum;
import com.z.admin.entity.vo.base.Result;
import com.z.admin.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * .
 * 异常统一处理，
 *
 * @author zhy
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 未知错误处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("服务器错误", e);
        return Result.fail(ResultCodeEnum.SERVER_ERROR);
    }

    /**
     * 自定义错误处理
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object serviceExceptionHandle(ServiceException e) {
        log.error("业务逻辑异常: {}", e.getCode().getValue());
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 参数校验错误处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.error("校验参数异常:{}", message);
        return Result.fail(message);
    }

}
