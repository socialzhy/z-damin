package com.z.admin.entity.enums;

import lombok.Getter;

/**
 * @author zhy
 * @description
 */
public enum RedisKeyEnum {

    USER_INFO("userInfo:"),

    PERMISSION("perMission:");

    @Getter
    private final String key;

    RedisKeyEnum(String key) {
        this.key = key;
    }

}
