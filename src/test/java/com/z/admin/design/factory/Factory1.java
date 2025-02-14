package com.z.admin.design.factory;

/**
 * @author zhy
 * @description 普通工厂模式
 */
public class Factory1 {

    public static Command getApp(Integer temp) {
        if (temp == 1) {
            return new Shell();
        } else if (temp == 2) {
            return new Dos();
        }
        throw new RuntimeException("not found app");
    }
}
