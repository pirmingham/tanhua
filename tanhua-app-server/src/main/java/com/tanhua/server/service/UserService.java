package com.tanhua.server.service;

import com.alibaba.fastjson.JSON;
import com.tanhua.autoconfig.template.SmsTemplate;
import com.tanhua.commons.utils.JwtUtils;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.model.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public final String REDIS_PREFIX ="CHECK_CODE_";
    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @DubboReference
    private UserApi userApi;
    public void login(String phone) {
        //1.随机生成6位验证码
        //String code = RandomStringUtils.randomNumeric(6);
        String code = "123456";
        //2.发送短信
//        smsTemplate.sendSms(phone,code);
        //3.将code放入redis中，并设置5分钟过期
        redisTemplate.opsForValue().set(REDIS_PREFIX+phone,code, Duration.ofMinutes(5));

    }
    /**
     * 验证码校验登录
     */
    public Map loginVerification(String mobile, String code) {
        //1、获取redis中存入的验证码
        String redisCode = redisTemplate.opsForValue().get(REDIS_PREFIX+mobile);
        //2、判断redis中的验证码是否存在并比较验证码是否一致
        if(StringUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            throw  new RuntimeException("验证码错误");
        }
        redisTemplate.delete(REDIS_PREFIX+mobile) ;// 清楚redis中的验证码数据
        //3、根据手机号码查询用户
        User user = userApi.findByMobile(mobile);
        //3、1 如果用户不存在，创建用户对象存入数据库
        boolean isNew = false;
        if(user == null) {
            user = new User();
            user.setMobile(mobile);
            user.setPassword(DigestUtils.md5Hex("123456"));
            Long userId = userApi.save(user);
            user.setId(userId);
            isNew = true;
        }
        String token = JwtUtils.createToken(mobile,user.getId());
        //4、将用户数据存入redis中
        String jsonString = JSON.toJSONString(user);//将对象转化为json字符串
        //存入redis，设置失效时间
        redisTemplate.opsForValue().set("TOKEN_"+token,jsonString,Duration.ofHours(1));
        //5、构造返回值
        Map map = new HashMap();
        map.put("token", token);
        map.put("isNew",isNew);
        return map;
    }
}
