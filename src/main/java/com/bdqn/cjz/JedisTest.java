package com.bdqn.cjz;

import redis.clients.jedis.Jedis;

/**
 * @author 34082
 */
public class JedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",9999);
        String ping = jedis.ping();
        System.out.println(ping);
    }


}
