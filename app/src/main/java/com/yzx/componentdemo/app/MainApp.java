package com.yzx.componentdemo.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yzx.base.app.AppConfig;
import com.yzx.base.app.BaseApp;
import com.yzx.componentdemo.BuildConfig;

/**
 * @author yzx
 * @date 2019/6/11
 * Description
 */
public class MainApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            //打开日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);

        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {

        for (String module : AppConfig.modules) {
            try {
                Class<?> aClass = Class.forName(module);
                BaseApp baseApp = (BaseApp) aClass.newInstance();
                baseApp.initModuleApp(application);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initModuleData(Application application) {
        for (String module : AppConfig.modules) {
            try {
                Class<?> aClass = Class.forName(module);
                BaseApp baseApp = (BaseApp) aClass.newInstance();
                baseApp.initModuleData(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
