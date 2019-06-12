package com.yzx.component_base.service;

/**
 * @author yzx
 * @date 2019/6/10
 * Description 账户服务
 */
public interface IAccountService {
    /**
     * 是否登录
     *
     * @return true login otherwise no login
     */
    boolean isLogin();

    /**
     * 获取userId
     *
     * @return if login return userId otherwise return null
     */
    String getUserId();
}
