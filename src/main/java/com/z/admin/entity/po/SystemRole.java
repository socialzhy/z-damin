package com.z.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.z.admin.entity.po.base.BasePo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author system
 * @since 2024-09-14
 */
@Getter
@Setter
@TableName("system_role")
public class SystemRole extends BasePo {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 权限父节点id
     */
    private Long parentId;
}
