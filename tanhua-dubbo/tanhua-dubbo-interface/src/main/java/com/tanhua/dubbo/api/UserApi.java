package com.tanhua.dubbo.api;

import com.tanhua.model.domain.User;

/**
 * 公共接口
 */
public interface UserApi {

    //根据手机号码查询用户
    User findByMobile(String mobile);

    Long save(User user);
}
