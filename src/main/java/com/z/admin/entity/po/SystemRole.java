package com.z.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.z.admin.entity.po.base.CommonPo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author system
 * @since 2024-09-06
 */
@Getter
@Setter
@TableName("system_role")
public class SystemRole extends CommonPo {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 权限父节点id
     */
    private Integer parentId;
}
