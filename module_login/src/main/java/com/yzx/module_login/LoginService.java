package com.yzx.module_login;

import com.yzx.component_base.service.IAccountService;

/**
 * @author yzx
 * @date 2019/6/11
 * Description
 */
public class LoginService implements IAccountService {
    @Override
    public boolean isLogin() {
        return true;
    }

    @Override
    public String getUserId() {
        return null;
    }
}
