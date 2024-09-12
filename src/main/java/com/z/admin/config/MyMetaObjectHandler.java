package com.z.admin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author zhy
 * @description mybatis-plus自动填充handler
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String FIELD_CREATE_BY = "createBy";
    private static final String FIELD_CREATE_TIME = "createTime";
    private static final String FIELD_UPDATE_BY = "updateBy";
    private static final String FIELD_UPDATE_TIME = "updateTime";
    private static final String FIELD_DELETED = "deleted";

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createBy = this.getFieldValByName(FIELD_CREATE_BY, metaObject);
        Object createTime = this.getFieldValByName(FIELD_CREATE_TIME, metaObject);
        Object deleted = this.getFieldValByName(FIELD_DELETED, metaObject);
        //根据名称设置属性值
        if (Objects.isNull(createBy)) {
            this.setFieldValByName(FIELD_CREATE_BY, 0L, metaObject);
        }
        if (Objects.isNull(createTime)) {
            this.setFieldValByName(FIELD_CREATE_TIME, LocalDateTime.now(), metaObject);
        }
        if (Objects.isNull(deleted)) {
            this.setFieldValByName(FIELD_DELETED, 0, metaObject);
        }

        this.updateFill(metaObject);
    }

    //使用mp实现修改操作,这个方法会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateBy = this.getFieldValByName(FIELD_UPDATE_BY, metaObject);
        Object updateTime = this.getFieldValByName(FIELD_UPDATE_TIME, metaObject);
        if (Objects.isNull(updateBy)) {
            this.setFieldValByName(FIELD_UPDATE_BY, 0L, metaObject);
        }
        if (Objects.isNull(updateTime)) {
            this.setFieldValByName(FIELD_UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }
}