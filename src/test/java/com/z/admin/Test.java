package com.z.admin;

import java.util.LinkedHashSet;

/**
 * @author zhy
 * @description
 */
public class Test {

    public static void main(String[] args) {
        LinkedHashSet<Long> atkLoseUidSet = new LinkedHashSet<>();
        atkLoseUidSet.add(2L);
        atkLoseUidSet.add(8L);
        atkLoseUidSet.add(5L);
        System.out.println(atkLoseUidSet);

        atkLoseUidSet.removeFirst();
        System.out.println(atkLoseUidSet);
    }
}
