package com.websocket.util;

public interface RedisService<K, V> {

    boolean hasKey(K key);

    void set(K key, V value);

    void set(K key, V value, long time);

    V get(K key);

    boolean setExpire(K key, long time);

    Long getExpire(K key);

    void delKey(K key);

    void rightPushList(K key, V value);

    V leftPopList(K key);

    Long increment(K key, Long delta);

    V hget(K key, Object hashKey);

    void hput(K key, Object hashKey, V value);
}
