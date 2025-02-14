package com.z.admin.design.factory;

/**
 * @author zhy
 * @description 普通工厂模式
 */
public class FactoryLinux {

    public static Command getApp() {
        return new Shell();
    }
}
