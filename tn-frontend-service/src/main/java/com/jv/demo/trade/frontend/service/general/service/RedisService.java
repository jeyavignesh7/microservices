package com.jv.demo.trade.frontend.service.general.service;

public interface RedisService {
    public void save(String key, String value);
    public String get(String key);
}
