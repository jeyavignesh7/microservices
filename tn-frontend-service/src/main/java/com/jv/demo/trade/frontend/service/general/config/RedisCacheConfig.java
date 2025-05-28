package com.jv.demo.trade.frontend.service.general.config;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
@CommonsLog
public class RedisCacheConfig {

    @Bean("redisCacheKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder key = new StringBuilder();
                if(target != null) {
                    key.append(target.getClass().getName() + "::");
                }
                key.append(method.getName());
                if(params != null) {
                    key.append("_" + Arrays.toString(params));
                }

                log.info(String.format("Key for redis cache: %s", key.toString()));

                return key.toString();
            }
        };
    }
}
