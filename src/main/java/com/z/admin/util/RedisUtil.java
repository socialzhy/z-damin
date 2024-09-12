package com.z.admin.util;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhy
 * @description redis操作工具类
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;




    public void set(String key,Object value){
        this.redisTemplate.opsForValue().set(key,value);
    }


    public Object get(String key){
        return this.redisTemplate.opsForValue().get(key);
    }

    public void del(String key){
        this.redisTemplate.delete(key);
    }
}
