package com.z.admin.util;

public class ObjectUtils extends org.springframework.util.ObjectUtils {

    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    public static boolean isNotEmpty(Object[] arr) {
        return !isEmpty(arr);
    }

}
