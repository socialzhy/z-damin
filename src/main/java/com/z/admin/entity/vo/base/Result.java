package com.z.admin.entity.vo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.z.admin.entity.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhy
 * @date 2022/10/10
 */
@Getter
@ToString
@NoArgsConstructor
public class Result<T> {

    /**
     * code码
     */
    private Integer code;

    /**
     * 操作结果
     */
    private String msg;

    /**
     * 业务数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private Result(ResultCodeEnum code) {
        this.code = code.getKey();
        this.msg = code.getValue();
    }

    private Result(ResultCodeEnum code, T data) {
        this(code);
        this.data = data;
    }

    /**
     * 内部使用，用于构造结果
     */
    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 内部使用，用于构造结果
     */
    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.SUCCESS.getKey(), ResultCodeEnum.SUCCESS.getValue());
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCodeEnum.SUCCESS.getKey(), ResultCodeEnum.SUCCESS.getValue(), data);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(ResultCodeEnum.SUCCESS.getKey(), msg, data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fail(ResultCodeEnum code) {
        return new Result<>(code);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultCodeEnum.FAIL.getKey(), msg);
    }

    public static <T> Result<T> fail(ResultCodeEnum code, T data) {
        return new Result<>(code, data);
    }

    public static <T> Result<T> fail(ResultCodeEnum code, String msg) {
        return new Result<>(code.getKey(), msg);
    }

    /**
     * 成功code=0
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return ResultCodeEnum.SUCCESS.getKey().equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

}
