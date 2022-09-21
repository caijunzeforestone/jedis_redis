package com.bdqn.cjz;



import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;


public class JedisTestDemo {


    @Test
    public void test1(){
        Jedis jedis = new Jedis("127.0.0.1",9999);

        jedis.set("name","cjz");
        String name = jedis.get("name");
        System.out.println(name+"\n");

        jedis.mset("kkk","v1","v2","v3");
        List<String> kkk = jedis.mget("kkk","v2");
        System.out.println(kkk+"\n");


        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key+"\n");
        }
    }

    @Test
    public void test2(){
        Jedis jedis = new Jedis("127.0.0.1",9999);
        jedis.lpush("key1", "c", "j", "z");
        List<String> key1 = jedis.lrange("key1", 0, -1);
        for (String s : key1) {
            System.out.println(s);
        }
    }


    @Test
    public void test3(){
        Jedis jedis = new Jedis("127.0.0.1",9999);
        jedis.sadd("names","o");
        jedis.sadd("names","n");
        jedis.sadd("names","e");
        Set<String> name = jedis.smembers("names");
        for (String s : name) {
            System.out.println(s);
        }
    }

    @Test
    public void test4(){
        Jedis jedis = new Jedis("127.0.0.1",9999);
        jedis.hset("u","name","18");
        String hget = jedis.hget("u", "name");
        System.out.println(hget);
    }

    @Test
    public void test5(){
        Jedis jedis = new Jedis("127.0.0.1",9999);
        jedis.zadd("c",888,"wuhan");
        Set<String> c = jedis.zrange("c", 0, -1);
        System.out.println(c);
    }


}
