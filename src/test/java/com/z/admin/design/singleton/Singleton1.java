package com.z.admin.design.singleton;

/**
 * @author zhy
 * @description 单例设计模式 - 饿汉模式
 */
public class Singleton1 {

    private static final Singleton1 instance = new Singleton1();

    /**
     * 私有化构造方法，保证单例对象不能被构建
     */
    private Singleton1() {
        throw new UnsupportedOperationException("Cannot instantiate Singleton");
    }

    public static Singleton1 getInstance() {
        return instance;
    }

}

