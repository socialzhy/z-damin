package com.z.admin.entity.enums;

import lombok.Getter;

/**
 * @author zhy
 * @description 操作权限类型
 */
@Getter
public enum SystemPermissionType {

    PAGE(1),
    OPERATE(2);

    private final Integer id;

    SystemPermissionType(Integer id) {
        this.id = id;
    }
}
