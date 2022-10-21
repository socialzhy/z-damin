package com.z.admin.entity.po.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhy
 * @description 基础po
 * @date 2022/10/17
 */
@Data
public class BasePo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键自增id */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 创建操作人 */
    @TableField(fill = FieldFill.INSERT)
    private Integer createBy;

    /** 插入时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新操作人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateBy;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
