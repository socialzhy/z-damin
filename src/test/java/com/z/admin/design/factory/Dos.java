package com.z.admin.design.factory;

/**
 * @author zhy
 * @description
 */
public class Dos implements Command {
    @Override
    public void open() {
        System.out.println("open dos ...");
    }
}
