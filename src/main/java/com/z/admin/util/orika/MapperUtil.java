package com.z.admin.util.orika;

import com.z.admin.util.orika.OrikaMapperFactory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 对象转换类
 */
public class MapperUtil {
    /**
     * 获取默认字段工厂
     */
    // private static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().mapNulls(false).build();
    /**
     * 默认字段实例
     */
    //private static final MapperFacade MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

    /**
     * 映射实体（默认字段）
     * 这种映射就是DTO字段名称和实体对象DO之间字段名称一致
     *
     * @param source  数据（对象）DO对象
     * @param toClass 映射类对象 DTO对象
     * @return 映射类对象
     */
    public static <S, T> T map(S source, Class<T> toClass) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }
        return getMapperFacade().map(source, toClass);
    }

    /**
     * 映射集合（默认字段）
     * 映射为集合的形式
     *
     * @param <T>
     * @param sourceList       数据（集合） DO对象
     * @param destinationClass 映射类对象 DTO对象
     * @return 映射类对象
     */
    public static <T> List<T> mapList(Collection<T> sourceList, Class<T> destinationClass) {
        if (ObjectUtils.isEmpty(sourceList)) {
            return null;
        }
        return getMapperFacade().mapAsList(sourceList, destinationClass);
    }

    /**
     * 有性能问题，慎用，大批量调用的接口不要使用
     * 映射实体（默认字段）
     * 这种映射就是DTO字段名称和实体对象DO之间字段名称一致
     *
     * @param sourceList       数据（对象）DO对象
     * @param destinationClass 映射类对象 DTO对象
     * @return 映射类对象
     */
    public static <S, T> List<T> mapList(Collection<S> sourceList, Class<S> sourceClass, Class<T> destinationClass, Boolean setNulls, Map<String, String> fields, String... skips) {
        if (ObjectUtils.isEmpty(sourceList)) {
            return null;
        }
        MapperFacade mapperFacade = getMapperFacade(sourceClass, destinationClass, setNulls, fields, skips);
        return mapperFacade.mapAsList(sourceList, destinationClass);
    }
    /**
     * 将PageInfo对象泛型中的Po对象转化为Vo对象
     *
     * @param pageInfoPO PageInfo<Po>对象</>
     * @param <P>        Po类型
     * @param <V>        Vo类型
     * @return
     */
//    public static <P, V> PageInfo<V> mapPage(PageInfo<P> pageInfoPO, Class<V> dClass) {
//        Page<V> page = new Page<>(pageInfoPO.getPageNum(), pageInfoPO.getPageSize());
//        page.setTotal(pageInfoPO.getTotal());
//        PageInfo<V> pageInfoVO = new PageInfo<>(page);
//        if (ObjectUtils.isNotEmpty(pageInfoPO.getList())) {
//            pageInfoVO.setList(mapList(pageInfoPO.getList(), dClass));
//        }
//        return pageInfoVO;
//    }

    /**
     * 获取自定义映射
     *
     * @param toClass   映射类
     * @param dataClass 数据映射类
     * @param configMap 自定义配置
     * @return 映射类对象
     */
    private static <E, T> MapperFacade getMapperFacade(Class<T> dataClass, Class<E> toClass, boolean setNulls, Map<String, String> configMap, String... skips) {
        MapperFactory factory = OrikaMapperFactory.getMapperFactory();
        ClassMapBuilder<T, E> classMapBuilder = factory.classMap(dataClass, toClass);
        classMapBuilder.mapNulls(setNulls);
        if (!ObjectUtils.isEmpty(skips)) {
            for (String skip : skips) {
                classMapBuilder.exclude(skip);
            }
            classMapBuilder.constructorB();
        }
        if (!ObjectUtils.isEmpty(configMap)) {
            configMap.forEach(classMapBuilder::field);
        }
        classMapBuilder.byDefault().register();
        return factory.getMapperFacade();
    }
    /**
     * 获取自定义映射
     *
     * @return 映射类对象
     */
    private static MapperFacade getMapperFacade() {
        MapperFactory factory = OrikaMapperFactory.getMapperFactory();
        return factory.getMapperFacade();
    }
}
