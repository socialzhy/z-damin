package com.z.admin.exception;

import com.z.admin.entity.vo.base.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ResultCodeEnum code;

    public ServiceException() {
        super(ResultCodeEnum.FAIL.getValue());
        this.code = ResultCodeEnum.FAIL;
    }

    public ServiceException(String msg) {
        super(msg);
        this.code = ResultCodeEnum.FAIL;
    }

    public ServiceException(ResultCodeEnum code, String msg) {
        super(msg);
        this.code = code;
    }

    public ServiceException(ResultCodeEnum code) {
        super(code.getValue());
        this.code = code;
    }

    public ServiceException(ResultCodeEnum code, Object... args) {
        super(String.format(code.getValue(), args));
        this.code = code;
    }
}