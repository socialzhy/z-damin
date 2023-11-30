package com.z.admin.util;

public class DataUtils extends org.springframework.util.ObjectUtils {

    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    public static boolean isNotEmpty(Object[] arr) {
        return !isEmpty(arr);
    }

    public static boolean idIsNotNull(Long id) {
        if (isEmpty(id)) {
            return false;
        }

        return id > 0L;
    }
}
