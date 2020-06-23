package com.websocket.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * @author 潘要东
 * @create 2018-04-14 10:50
 * @descriptions <p></p>
 *
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作hash
 * redisTemplate.opsForList();//操作list
 * redisTemplate.opsForSet();//操作set
 * redisTemplate.opsForZSet();//操作有序set
 */
@Configuration
public class RedisServiceImpl<K, V> implements RedisService<K, V> {

    private RedisTemplate<K, V> redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }
    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:51
     * @descriptions <p>检查key是否存在，返回boolean值</p>
     * @params [key]
     * @return boolean
     ****************************************************************************************************/
    @Override
    public boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:51
     * @descriptions <p>设置缓存值</p>
     * @params [key, value]
     * @return void
     ****************************************************************************************************/
    @Override
    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:52
     * @descriptions <p>设置缓存值并设置有效期</p>
     * @params [key, value, time]
     * @return void
     ****************************************************************************************************/
    @Override
    public void set(K key, V value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:52
     * @descriptions <p>获取缓存值</p>
     * @params [key]
     * @return V
     ****************************************************************************************************/
    @Override
    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:53
     * @descriptions <p>设置key的过期时间</p>
     * @params [key, time]
     * @return boolean
     ****************************************************************************************************/
    @Override
    public boolean setExpire(K key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:53
     * @descriptions <p>获取缓存过期时间</p>
     * @params [key]
     * @return java.lang.Long
     ****************************************************************************************************/
    @Override
    public Long getExpire(K key) {
        return redisTemplate.getExpire(key);
        // 根据key获取过期时间并换算成指定单位
//        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:53
     * @descriptions <p>删除缓存</p>
     * @params [key]
     * @return void
     ****************************************************************************************************/
    @Override
    public void delKey(K key) {
        redisTemplate.delete(key);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:53
     * @descriptions <p>将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）</p>
     * @params [key, value]
     * @return void
     ****************************************************************************************************/
    @Override
    public void rightPushList(K key, V value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:54
     * @descriptions <p>弹出最左边的元素，弹出之后该值在列表中将不复存在</p>
     * @params [key]
     * @return V
     ****************************************************************************************************/
    @Override
    public V leftPopList(K key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:54
     * @descriptions <p>设置一个自增的数据</p>
     * @params [key, delta]
     * @return java.lang.Long
     ****************************************************************************************************/
    @Override
    public Long increment(K key, Long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:55
     * @descriptions <p>从键中的哈希获取给定hashKey的值</p>
     * @params [key, hashKey]
     * @return V
     ****************************************************************************************************/
    @Override
    public V hget(K key, Object hashKey) {
        return (V) redisTemplate.opsForHash().get(key, hashKey);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/14 10:55
     * @descriptions <p>设置散列hashKey的值</p>
     * @params [key, hashKey, value]
     * @return void
     ****************************************************************************************************/
    @Override
    public void hput(K key, Object hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }
}
