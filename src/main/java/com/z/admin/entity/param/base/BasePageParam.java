package com.z.admin.entity.param.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.z.admin.entity.po.base.BasePo;
import lombok.Data;

/**
 * @author zhy
 * @description
 * @date 2022/10/20
 */
@Data
public class BasePageParam<T extends BasePo> {

    /**
     * 当前页
     */
    private Integer currentPage = 1;

    /**
     * 每页显示数量
     */
    private Integer pageSize = 10;

    /**
     * 从form中获取page参数，用于分页查询参数
     */
    public Page<T> getPage() {
        return new Page<>(this.getCurrentPage(), this.getPageSize());
    }
}
