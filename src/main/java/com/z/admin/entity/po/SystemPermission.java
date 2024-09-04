package com.z.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.z.admin.entity.po.base.CommonPo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author system
 * @since 2024-09-04
 */
@Getter
@Setter
@TableName("system_permission")
public class SystemPermission extends CommonPo {

    /**
     * 请求方法
     */
    private String method;

    /**
     * 操作路由
     */
    private String path;

    /**
     * 1页面权限 2操作权限
     */
    private Integer type;

    /**
     * 权限父节点id
     */
    private Integer parentId;

    /**
     * 允许匿名访问 0 不允许 1允许
     */
    private Integer allowAnonymous;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
