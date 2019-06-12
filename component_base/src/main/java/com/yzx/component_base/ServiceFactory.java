package com.yzx.component_base;

import com.yzx.component_base.empty_service.NoLoginServiceImp;
import com.yzx.component_base.service.IAccountService;

/**
 * @author yzx
 * @date 2019/6/10
 * Description 服务工厂
 */
public class ServiceFactory {

    private IAccountService accountService;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return Inner.serviceFactory;
    }

    private static class Inner {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public IAccountService getAccountService() {
        if (accountService == null) {
            accountService = new NoLoginServiceImp();
        }
        return accountService;
    }
}
