package com.z.admin.util;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author zhy
 * @date 2022-10-14
 * @description 对象转换工具类
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 将source中不为空的字段，拷贝(覆盖)到target中
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) throws BeansException {
        if (ObjectUtils.isEmpty(source)) {
            return;
        }
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }

    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param source: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     */
    public static <S, T> T copyPropertiesIgnoreNull(S source, Supplier<T> target) {
        return copyPropertiesIgnoreNull(source, target, null);
    }

    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param source:   数据源类
     * @param target:   目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     */
    public static <S, T> T copyPropertiesIgnoreNull(S source, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        T t = target.get();
        copyPropertiesIgnoreNull(source, t);
        if (callBack != null) {
            // 回调
            callBack.callBack(source, t);
        }
        return t;
    }

    /**
     * 获取对象所有为null的字段
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 集合数据的拷贝
     *
     * @param sources: 数据源类
     * @param target:  目标类::new(eg: UserVO::new)
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param sources:  数据源类
     * @param target:   目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;

    }

    @FunctionalInterface
    public interface BeanCopyUtilCallBack<S, T> {

        /**
         * 定义默认回调方法
         */
        void callBack(S t, T s);
    }

}
