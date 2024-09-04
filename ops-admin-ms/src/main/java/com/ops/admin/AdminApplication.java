package com.ops.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
//        Jedis jedis = new Jedis();
//        jedis.hset("user#1", "name", "Peter");
//        jedis.hset("user#1", "job", "politician");
//
//        String name = jedis.hget("user#1", "name");
//        System.out.println(name);
//
//        Map<String, String> fields = jedis.hgetAll("user#1");
//        String job = fields.get("job");
//        System.out.println(job);
//
//        Map<String, Double> scores = new HashMap<>();
//
//        scores.put("PlayerOne", 30000.0);
//        scores.put("PlayerTwo", 1500.0);
//        scores.put("PlayerThree", 8200.0);
//
//            scores.entrySet().forEach(playerScore -> {
//                jedis.zadd("ranking", playerScore.getValue(), playerScore.getKey());
//            });
//
//        String player = jedis.zrevrange("ranking", 0, 0).iterator().next();
//        long rank = jedis.zrevrank("ranking", "PlayerOne");
//        System.out.println(player);
//        System.out.println(rank);
//
//        String friendsPrefix = "friends#";
//        String userOneId = "4352523";
//        String userTwoId = "5552321";
//
//        Transaction t = jedis.multi();
//        t.sadd(friendsPrefix + userOneId, userTwoId);
//        t.sadd(friendsPrefix + userTwoId, userOneId);
//        t.exec();
    }
}