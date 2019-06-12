package com.yzx.base.arouter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.yzx.component_base.ServiceFactory;

/**
 * @author yzx
 * @date 2019/6/11
 * Description 登陆跳转拦截器
 */
@Interceptor(priority = 8)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {


        String loginKey = postcard.getExtras().getString("loginKey");
        //如果是登录tag
        if (TextUtils.equals(loginKey, ARouterNavUtils.TAG_LOGIN)) {
            if (ServiceFactory.getInstance().getAccountService().isLogin()) {
                callback.onContinue(postcard);
            } else {
                ARouterNavUtils.nav(ARouterPath.LOGIN);
            }
        } else {
            callback.onContinue(postcard);
        }


    }

    @Override
    public void init(Context context) {

    }
}
