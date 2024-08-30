package cn.wolfcode.redis;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public enum CommonRedisKey {
    // 用户认证token key
    USER_TOKEN("userToken:", TimeUnit.MINUTES, 30);

    private String prefix;
    private TimeUnit unit;
    private int expireTime;

    CommonRedisKey(String prefix, TimeUnit unit, int expireTime) {
        this.prefix = prefix;
        this.unit = unit;
        this.expireTime = expireTime;
    }

    public String getRealKey(String key) {
        return this.prefix + key;
    }
}