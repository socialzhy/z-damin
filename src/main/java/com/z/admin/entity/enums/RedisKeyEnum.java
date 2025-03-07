package com.z.admin.entity.enums;

import lombok.Getter;

/**
 * @author zhy
 * @description redisKey
 */
@Getter
public enum RedisKeyEnum {

    USER_INFO("userInfo:"),

    PERMISSION("perMission:"),

    ROLE_PERMISSION("rolePerMission:");

    private final String key;

    RedisKeyEnum(String key) {
        this.key = key;
    }

}
