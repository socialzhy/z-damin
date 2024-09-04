package com.z.admin.entity.po;

import com.z.admin.entity.po.base.CommonPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @date 2022-10-17
 * @description 角色类实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
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
