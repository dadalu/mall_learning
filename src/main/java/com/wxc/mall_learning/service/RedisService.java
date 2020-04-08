package com.wxc.mall_learning.service;


public interface RedisService {
     void set(String key, String value);

     String get(String key);

     boolean expire(String key,long expire);

     void remove(String key);

     long increment(String key,long delta);

}
