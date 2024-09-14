package com.z.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.z.admin.entity.po.base.BasePo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author system
 * @since 2024-09-14
 */
@Getter
@Setter
@TableName("system_permission")
public class SystemPermission extends BasePo {

    /**
     * 请求方法
     */
    private String method;

    /**
     * 操作路由
     */
    private String path;

    /**
     * 0超管权限 1页面权限 2操作权限
     */
    private Integer type;

    /**
     * 权限父节点id
     */
    private Long parentId;

    /**
     * 权限等级 1允许匿名访问  2登录即可访问  3须有此权限可访问
     */
    private Integer accessLevel;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
