package com.z.admin.entity.form.base;

import com.z.admin.entity.po.base.BasePo;
import com.z.admin.util.BeanUtils;
import lombok.Data;

import java.lang.reflect.ParameterizedType;

/**
 * @author zhy
 * @description 基础form
 * @date 2022/10/19
 */
@Data
public abstract class BaseForm <T extends BasePo>{

    /**
     * form 转 po
     * @param clazz po的class
     */
    public T toPo(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyPropertiesIgnoreNull(this, t);
        return t;
    }

    /**
     * form 转 po
     */
    @SuppressWarnings("unchecked")
    public T toPo() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyPropertiesIgnoreNull(this, t);
        return t;
    }
}
