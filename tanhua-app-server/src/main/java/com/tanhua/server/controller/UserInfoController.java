package com.tanhua.server.controller;

import com.tanhua.commons.utils.JwtUtils;
import com.tanhua.model.domain.UserInfo;
import com.tanhua.model.vo.UserInfoVo;
import com.tanhua.server.service.UserInfoService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Administrator
 */
@RestController
@RequestMapping(path = "/users")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 查询用户基础信息
     *
     * @param userID
     * @param token
     * @return
     */
    @GetMapping
    public ResponseEntity userInfo(Long userID, @RequestHeader("Authorization") String token) {
        //1. 校验token
        boolean verifyToken = JwtUtils.verifyToken(token);
        if(!verifyToken) {
            return ResponseEntity.status(401).body(null);
        }
        //2. 获取token中的用户信息
        Claims claims = JwtUtils.getClaims(token);
        String id = claims.get("id").toString();

        if (userID == null) {
            userID = Long.valueOf(id);
        }

        UserInfoVo userInfo = userInfoService.findById(userID);

        return ResponseEntity.ok(userInfo);
    }

    /**
     * 更新用户资料
     */
    @PutMapping
    public ResponseEntity updateUserInfo(@RequestBody UserInfo userInfo, @RequestHeader("Authorization") String token) {
        //1、判断token是否合法
        boolean verifyToken = JwtUtils.verifyToken(token);
        if(!verifyToken) {
            return ResponseEntity.status(401).body(null);
        }
        //2、获取id
        Claims claims = JwtUtils.getClaims(token);
        Integer id = (Integer) claims.get("id");

        //设置id
        userInfo.setId(Long.valueOf(id));
        userInfoService.update(userInfo);
        return ResponseEntity.ok(null);
    }

}