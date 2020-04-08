package com.wxc.mall_learning.service.impl;

import com.wxc.mall_learning.service.RedisService;

public class RedisServiceImpl implements RedisService {

    @Override
    public void set(String key, String value) {

    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean expire(String key, long expire) {
        return false;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public long increment(String key, long delta) {
        return 0;
    }
}
