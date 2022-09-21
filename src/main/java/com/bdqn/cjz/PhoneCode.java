package com.bdqn.cjz;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {

    public static void main(String[] args) {
        verifyCode("13908640909");
    }


    public static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }

    public static void verifyCode(String phone) {
        Jedis jedis = new Jedis("127.0.0.1", 9999);
        String countKey = "VerifyCode" + phone + ":count";
        String codeKey = "VerifyCode" + phone + ":code";
        String count = jedis.get(countKey);
        if (count == null) {//没有发送次数
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            System.out.println("每个手机号一天最多发送三次验证码");
            jedis.close();
            return;
        }
        String vcode = getCode();
        System.out.println(vcode);
        jedis.setex(codeKey, 120, vcode);
        jedis.close();
    }

    public static void getRedisCode(String phone, String code) {
        Jedis jedis = new Jedis("127.0.0.1", 9999);
        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);
        if (redisCode.equals(code)) {
            System.out.println("验证码校验成功");
        } else {
            System.out.println("验证码校验失败");
        }
        jedis.close();
    }

}
