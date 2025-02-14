package com.z.admin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.z.admin.entity.enums.RedisKeyEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhy
 * @description redis操作工具类
 */
@Component
@Slf4j
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    // ============================= common =============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 是否成功
     */
    public Boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis expire error , key : {} , time : {}", key, time, e);
            return false;
        }
    }

    /**
     * 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒)，返回 -1 代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return true 存在 false 不存在
     */
    public Boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            log.error("redis hasKey error , key : {}", key, e);
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Set.of(key));
            }
        }
    }

    // ============================ String =============================

    /**
     * 从 Redis 获取数据并转换为目标对象
     *
     * @param redisKeyEnum key的枚举
     * @param key          Redis 中存储的 key
     * @param clazz        目标类的类型
     * @param <T>          泛型类型
     * @return 返回反序列化后的对象
     */
    public <T> T getObjectFromRedis(RedisKeyEnum redisKeyEnum, String key, Class<T> clazz) {
        Object jsonData = redisTemplate.opsForValue().get(redisKeyEnum.getKey() + key);
        if (jsonData != null) {
            try {
                // 将 JSON 字符串转换为目标类的对象
                return objectMapper.convertValue(jsonData, clazz);
            } catch (Exception e) {
                log.error("redis parse error , key : {} , clazz : {}", key, clazz.getName(), e);
            }
        }
        return null;
    }

    /**
     * 从 Redis 获取数据并转换为目标对象
     *
     * @param key   Redis 中存储的 key
     * @param clazz 目标类的类型
     * @param <T>   泛型类型
     * @return 返回反序列化后的对象
     */
    public <T> T getObjectFromRedis(String key, Class<T> clazz) {
        Object jsonData = redisTemplate.opsForValue().get(key);
        if (jsonData != null) {
            try {
                // 将 JSON 字符串转换为目标类的对象
                return objectMapper.convertValue(jsonData, clazz);
            } catch (Exception e) {
                log.error("redis parse error , key : {} , clazz : {}", key, clazz.getName(), e);
            }
        }
        return null;
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param redisKeyEnum key枚举
     * @param key          键
     * @param value        值
     * @return true成功 false失败
     */
    public Boolean set(RedisKeyEnum redisKeyEnum, String key, Object value) {
        try {
            redisTemplate.opsForValue().set(redisKeyEnum.getKey() + key, value);
            return true;
        } catch (Exception e) {
            log.error("redis set error , key : {} , value : {}", key, value, e);
            return false;
        }
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis set error , key : {} , value : {}", key, value, e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public Boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("redis set error , key : {} , value : {} , time : {}", key, value, time, e);
            return false;
        }
    }

    // ================================ Hash ================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 对应的值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取 hashKey 对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public Boolean hSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("redis hSet error , key : {} , map : {} ", key, map, e);
            return false;
        }
    }

    /**
     * 向一张 hash 表中放入数据, 如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false 失败
     */
    public Boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("redis hSet error , key : {} , item : {} , value : {}", key, item, value, e);
            return false;
        }
    }

    // ============================ Set =============================

    /**
     * 根据 key 获取 Set 中的所有值
     *
     * @param key 键
     * @return Set 中的所有值
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("redis sGet error , key : {} ", key, e);
            return null;
        }
    }

    /**
     * 将数据放入 set 缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sSet(String key, Object... values) {
        try {
            Long add = redisTemplate.opsForSet().add(key, values);
            return DataUtils.isEmpty(add) ? 0 : add;
        } catch (Exception e) {
            log.error("redis sGet error , key : {} , values : {}", key, values, e);
            return 0L;
        }
    }

    // ============================ List =============================

    /**
     * 获取 list 缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1 代表所有值
     * @return list 缓存的内容
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("redis lGet error , key : {} , start : {} , end : {}", key, start, end, e);
            return null;
        }
    }

    /**
     * 将 list 放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 是否成功
     */
    public Boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis lSet error , key : {} , value : {}", key, value, e);
            return false;
        }
    }
}
