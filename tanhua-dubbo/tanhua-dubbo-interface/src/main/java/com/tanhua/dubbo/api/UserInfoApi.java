package com.tanhua.dubbo.api;

import com.tanhua.model.domain.UserInfo;

/**
 * 用户基础信息公共接口
 */
public interface UserInfoApi {

    /**
     * 根据用户ID查询用户信息
     */
    public UserInfo findById(Long id);

    /**
     * 更新用户信息
     */
    public void update(UserInfo userInfo);

    /**
     * 保存用户信息
     */
    public void save(UserInfo userInfo);
}