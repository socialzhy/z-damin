package com.z.admin.entity.vo.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果码枚举
 */
public enum ResultCodeEnum {

    SUCCESS(0, "操作成功"),
    SERVER_ERROR(500, "服务器错误"),

    //region    通用错误码 40000-40099
    FAIL(40000, "通用错误码，具体参见message"),
    //endregion

    //region 服务器层错误码 50000-59999
    //重复操作
    REPEAT_OPERATION(50000,"您已经操作过了，请稍等");
    //endregion


    private Integer key;
    private String value;

    ResultCodeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Map<Integer, String> valueMap() {
        ResultCodeEnum[] enums = ResultCodeEnum.values();
        Map<Integer, String> resultCodeMap = new HashMap<>(enums.length);
        for (ResultCodeEnum e : enums) {
            resultCodeMap.put(e.key, e.value);
        }
        return resultCodeMap;
    }

    public static ResultCodeEnum valueOf(Integer code) {
        ResultCodeEnum[] enums = ResultCodeEnum.values();
        Map<Integer, String> resultCodeMap = new HashMap<>(enums.length);
        for (ResultCodeEnum e : enums) {
            if(e.getKey().equals(code)){
                return e;
            }
        }
        return null;
    }
}
