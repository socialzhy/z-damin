package com.z.admin.entity.po.system;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.z.admin.entity.po.base.CommonPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @date 2022-10-17
 * @description 用户类实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemUser extends CommonPo {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 姓名
     */
    private String name;

    /** 是否禁用 */
    @TableField(fill = FieldFill.INSERT)
    private Integer isDisabled;

    /** 是否删除 */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;
}
