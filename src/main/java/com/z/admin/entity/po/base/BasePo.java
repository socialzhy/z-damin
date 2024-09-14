package com.z.admin.entity.po.base;

import com.baomidou.mybatisplus.annotation.*;
import com.z.admin.entity.vo.base.BaseVo;
import com.z.admin.util.BeanUtils;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhy
 * @description 基础po
 */
@Data
public class BasePo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键自增id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建操作人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 插入时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新操作人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableLogic //逻辑删除注解
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;


    public <T extends BaseVo> T toVO(Class<T> clazz) {
        T p = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, p);
        return p;
    }

}
