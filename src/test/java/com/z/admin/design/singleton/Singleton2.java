package com.z.admin.design.singleton;

import com.z.admin.util.DataUtils;

/**
 * @author zhy
 * @description 单例设计模式 - 懒汉模式
 */
public class Singleton2 {

    private static volatile Singleton2 instance = null;

    /** 私有化构造方法，保证外部无法调用 */
    private Singleton2() {
    }

    /**
     * 单线程场景可用，多线程场景可能被重复创建（不推荐使用）
     */
    public static Singleton2 getInstance() {
        if (DataUtils.isEmpty(instance)){
            instance = new Singleton2();
        }
        return instance;
    }

    /**
     * 添加synchronized关键字，多线程场景下可用，但是每次都锁，性能较低（不推荐使用）
     */
    public static synchronized Singleton2 getInstance2() {
        if (DataUtils.isEmpty(instance)){
            instance = new Singleton2();
        }
        return instance;
    }

    /**
     * 双重检测锁式单例，如果Singleton2没有被volatile关键字修饰，可能会有指令重排导致的问题
     */
    public static Singleton2 getInstance3() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();//erro
                }
            }
        }
        return instance;
    }

}

