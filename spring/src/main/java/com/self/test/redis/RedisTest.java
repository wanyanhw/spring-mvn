package com.self.test.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 测试redis
 */
public class RedisTest {

    static private Jedis jedis = new Jedis();

    /**
     * 向redis中添加数据
     * @param key 唯一键
     * @param value 唯一键对应的值
//     * @param expireTimes 过期时间,单位：秒
     */
    static public void addObject2Redis(String key, String value) {
        String result = jedis.set(key, value);
//        jedis.expire(key, expireTimes);
        System.out.println("结果：" + result);
    }

    /**
     * 通过key值获取redis中的value
     * @param key 唯一键
     */
    static public void getValueFromRedisByKey(String key) {
        String result = jedis.get(key);
        System.out.println("获取值为：" + result);
    }

    /**
     * 删除redis中的key
     * @param key 唯一键
     */
    static public void delKeyFromRedis(String key) {
        long i = jedis.del(key);
        System.out.println("删除数据条数：" + i);
    }



    public static void main(String[] args) {
//        addObject2Redis("abc", "这是汉字！！");
//        getValueFromRedisByKey("我的");
//        getValueFromRedisByKey("abc");
        delKeyFromRedis("我的");
        Set result = jedis.keys("*");
        System.out.println(result);
    }
}
