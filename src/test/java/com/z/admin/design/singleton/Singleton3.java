package com.z.admin.design.singleton;

/**
 * @author zhy
 * @description 单例设计模式 - 内部类也能达到延迟加载的效果，内部类只有在使用的时候才会被装载
 */
public class Singleton3 {

    /* 私有构造方法，防止被实例化 */
    private Singleton3() {
    }

    /* 获取实例 */
    public static Singleton3 getInstance() {
        return SingletonFactory.instance;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }

    /* 此处使用一个内部类来维护单例 */
    private static class SingletonFactory {
        private static final Singleton3 instance = new Singleton3();
    }

}
