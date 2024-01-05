package com.z.admin.util;

import com.z.admin.entity.vo.base.ResultCodeEnum;
import com.z.admin.exception.ServiceException;
import com.z.admin.config.security.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zhy
 * @description 登录工具类
 * @date 2022/12/22
 */
public class LoginUtil {

    public static Long getLoginUserId() {
        try {
            UserDetail user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user.getSystemUser().getId();
        } catch (Exception e) {
            throw new ServiceException(ResultCodeEnum.AUTHENTICATION_FAILED);
        }


    }
}
