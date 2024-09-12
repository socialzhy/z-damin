package com.z.admin.entity.enums;

import lombok.Getter;

/**
 * @author zhy
 * @description 操作权限类型
 */
@Getter
public enum SystemPermissionLevel {

    /**
     * 匿名访问
     */
    ANONYMOUS(1),
    /**
     * 登录访问
     */
    LOGIN(2),
    /**
     * 鉴权访问
     */
    AUTH(3);

    private final Integer id;

    SystemPermissionLevel(Integer id) {
        this.id = id;
    }
}
