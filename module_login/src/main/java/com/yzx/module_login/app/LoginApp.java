package com.yzx.module_login.app;

import android.app.Application;
import android.util.Log;

import com.yzx.base.app.BaseApp;

/**
 * @author yzx
 * @date 2019/6/11
 * Description
 */
public class LoginApp extends BaseApp {

    public static final String TAG = "LoginApp";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: LoginApp");
    }

    @Override
    public void initModuleApp(Application application) {
        Log.i(TAG, "initModuleApp: LoginApp");
    }

    @Override
    public void initModuleData(Application application) {
        Log.i(TAG, "initModuleData: LoginApp");
    }
}
