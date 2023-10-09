package com.tanhua.server.service;

import com.tanhua.autoconfig.template.SmsTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class UserService {

    public final String REDIS_PREFIX ="CHECK_CODE_";
    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    public void login(String phone) {
        //1.随机生成6位验证码
        String code = RandomStringUtils.randomNumeric(6);
        //2.发送短信
        smsTemplate.sendSms(phone,code);
        //3.将code放入redis中，并设置5分钟过期
        redisTemplate.opsForValue().set(REDIS_PREFIX+phone,code, Duration.ofMinutes(5));

    }
}
