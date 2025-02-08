package com.z.admin.design.factory;

/**
 * @author zhy
 * @description
 */
public class Shell implements Command {
    @Override
    public void open() {
        System.out.println("open shell ..");
    }
}
