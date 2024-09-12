package com.z.admin.entity.vo.base;

import lombok.Data;

/**
 * @author zhy
 * @description 常规保存返回的vo，只包含主键id字段
 */
@Data
public class BaseVo {

    /**
     * 主键id
     */
    private Long id;
}
