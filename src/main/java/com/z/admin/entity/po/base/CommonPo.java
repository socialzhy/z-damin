package com.z.admin.entity.po.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhy
 * @description 常规po
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonPo extends BasePo {

    /**
     * 是否删除
     */
    @TableLogic //逻辑删除注解
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
