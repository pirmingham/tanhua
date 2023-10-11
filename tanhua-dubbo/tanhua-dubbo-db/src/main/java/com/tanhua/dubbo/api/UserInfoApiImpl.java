package com.tanhua.dubbo.api;

import com.tanhua.dubbo.mapper.UserInfoMapper;
import com.tanhua.model.domain.UserInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户基础信息的dubbo实现类
 */
@DubboService
public class UserInfoApiImpl implements UserInfoApi {

    @Autowired
    private UserInfoMapper userInfoMapper;

    //根据id查询
    @Override
    public UserInfo findById(Long id) {
        return userInfoMapper.selectById(id);
    }

    //更新
    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    //保存
    @Override
    public void save(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }
}