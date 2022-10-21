package com.z.admin.entity.vo.system;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhy
 * @description
 * @date 2022/9/23
 */
@Data
public class UserInfoVo {

    private String avatar;

    private String introduction;

    private String name;

    private List<String> roles = new ArrayList<>();

    private List<String> permissions = new ArrayList<>();
}
