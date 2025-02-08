package com.z.admin.design.factory;

/**
 * @author zhy
 * @description
 */
public interface SoftwareFactory {

    OperateSystem createOperateSystem();

    Command open();

}
