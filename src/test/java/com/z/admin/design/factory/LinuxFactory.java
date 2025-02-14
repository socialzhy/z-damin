package com.z.admin.design.factory;

/**
 * @author zhy
 * @description
 */
public class LinuxFactory implements SoftwareFactory{
    @Override
    public OperateSystem createOperateSystem() {
        return new Linux();
    }

    @Override
    public Command open() {
        return new Shell();
    }
}
