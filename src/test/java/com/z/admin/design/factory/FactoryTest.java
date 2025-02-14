package com.z.admin.design.factory;

/**
 * @author zhy
 * @description 工厂模式测试
 */
public class FactoryTest {

    public static void main(String[] args) {
//        factory1();
//        factory2();
        factory3();
    }

    /**
     * 普通工厂模式， 一个工厂，根据参数返回不同的类型
     */
    public static void factory1() {
        Command shell = Factory1.getApp(1);
        shell.open();

        Command dos = Factory1.getApp(2);
        dos.open();
    }

    /**
     * 普通工厂模式， 多个工厂，每个工厂返回指定的类型
     */
    public static void factory2() {
        Command dos = FactoryWindows.getApp();
        dos.open();

        Command shell = FactoryLinux.getApp();
        shell.open();
    }

    /**
     * 抽象工厂，多个接口进行分类管理，每个工厂管理一种类型
     */
    public static void factory3() {
        SoftwareFactory windowsFactory = new WindowsFactory();
        OperateSystem windowsSystem = windowsFactory.createOperateSystem();
        windowsSystem.run();
        Command dos = windowsFactory.open();
        dos.open();

        LinuxFactory linuxFactory = new LinuxFactory();
        OperateSystem linuxSystem = linuxFactory.createOperateSystem();
        linuxSystem.run();
        Command shell = linuxFactory.open();
        shell.open();
    }

}


