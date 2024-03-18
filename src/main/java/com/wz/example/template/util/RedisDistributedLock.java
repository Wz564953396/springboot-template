package com.wz.example.template.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisDistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDistributedLock.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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


    public boolean lock1(String key) {
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {

            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                StringRedisConnection conn = (StringRedisConnection) connection;

                return conn.setNX(key, key);
            }
        });
    }

    public boolean releaseLock1(String key) {


        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {

            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                StringRedisConnection conn = (StringRedisConnection) connection;

                return conn.eval(script, ReturnType.BOOLEAN, 1, key, key);
            }
        });
    }

    public boolean lock2(String lockKey) {
        return this.lock2(lockKey, 0);
    }

    public boolean lock2(String lockKey, long waitTime) {
        return this.lock2(lockKey, waitTime, -1);
    }

    /**
     * redis分布式锁，看门狗机制，
     * 注意！！！redisson是以Hash结构保存的锁，默认情况下，hash中的key为 #{threadId}，而在非阻塞网络IO模型中，一个线程处理多个请求，
     * 因此会出现一个线程下处理的多个请求拿到同一把锁的情况。一定要用lockKey进行区分！！！
     * @param lockKey
     * @param waitTime
     * @param leaseTime
     * @return
     */
    public boolean lock2(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseLock2(String lockKey) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        lock.unlock();
    }

}
