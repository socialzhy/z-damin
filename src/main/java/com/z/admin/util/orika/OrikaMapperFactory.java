package com.z.admin.util.orika;

import ma.glasnost.orika.MapperFactory;

/**
 * @author zhy
 * @description OrikaMapperFactory 单例工厂
 */
public final class OrikaMapperFactory {

    private static volatile MapperFactory INSTANCE = null;

    private OrikaMapperFactory() {

    }

    public static MapperFactory getMapperFactory() {
        if (INSTANCE == null) {
            synchronized (OrikaMapperFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrikaMapper().getFactory();
                }
            }
        }
        return INSTANCE;
    }

}