package com.self.jedis;

import com.self.utils.SerializeUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class RedisCache implements Cache {

    private static Logger logger = Logger.getLogger(RedisCache.class.toString());

    private static JedisConnectionFactory jedisConnectionFactory;

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final String id;

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info("MybatisRedisCache:id = " + id);
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void putObject(Object key, Object value) {
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = jedisConnectionFactory.getConnection();
            jedisConnection.set(SerializeUtils.transformObject2Byte(key), SerializeUtils.transformObject2Byte(value));
            logger.info("Key has been in the redis database!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
    }

    public Object getObject(Object key) {
        Object object = null;
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = jedisConnectionFactory.getConnection();
            byte[] bytes = jedisConnection.get(SerializeUtils.transformObject2Byte(key));
            if (bytes != null) {
                object = SerializeUtils.transformByte2Object(bytes);
                logger.info("Get data from redis database!");
            }
        } catch (Exception e) {
            logger.info("Exception happened when get data from redis database!!");
            e.printStackTrace();
        } finally {
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
        return object;
    }

    public Object removeObject(Object key) {
        Object object = null;
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = jedisConnectionFactory.getConnection();
            // 通过key设置过期时间为0s，即删除key
            object = jedisConnection.expire(SerializeUtils.transformObject2Byte(key), 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
        return object;
    }

    public void clear() {
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = jedisConnectionFactory.getConnection();
            jedisConnection.flushDb();
            jedisConnection.flushAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
    }

    public int getSize() {
        int size = 0;
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = jedisConnectionFactory.getConnection();
            size = Integer.parseInt(jedisConnection.dbSize().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
        return size;
    }

    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
