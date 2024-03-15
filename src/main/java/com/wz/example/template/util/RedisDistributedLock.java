package com.wz.example.template.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisDistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLock.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 锁前缀
     */
    private static final String KEY_PREFIX = "DISTRIBUTED_LOCK:";

    /**
     * 获取锁最大等待时间（毫秒）
     */
    private static final long DEFAULT_WAIT_TIME = 10 * 1000;

    public boolean lock(String lockKey) {
        return this.lock(lockKey, 0);
    }

    public boolean lock(String lockKey, long waitTime) {
        return this.lock(lockKey, waitTime, -1);
    }

    public boolean lock(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseLock(String lockKey) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        lock.unlock();
    }

}
