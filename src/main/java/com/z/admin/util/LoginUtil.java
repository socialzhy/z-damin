package com.z.admin.util;

import com.z.admin.entity.enums.ResultCodeEnum;
import com.z.admin.exception.ServiceException;
import com.z.admin.security.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zhy
 * @description 登录工具类
 */
public class LoginUtil {

    public static UserDetail getLoginUser() {
        try {
            return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException(ResultCodeEnum.AUTHENTICATION_FAILED);
        }


    }
}
