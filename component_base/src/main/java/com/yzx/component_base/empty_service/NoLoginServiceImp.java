package com.yzx.component_base.empty_service;

import com.yzx.component_base.service.IAccountService;

/**
 * @author yzx
 * @date 2019/6/10
 * Description 未登录服务
 */
public class NoLoginServiceImp implements IAccountService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getUserId() {
        return null;
    }
}
