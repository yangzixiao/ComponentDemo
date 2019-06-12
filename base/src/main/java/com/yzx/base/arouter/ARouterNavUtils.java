package com.yzx.base.arouter;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author yzx
 * @date 2019/6/11
 * Description ARouter 跳转工具类
 */
public class ARouterNavUtils {

    public static final String TAG_LOGIN = "loginTag";

    /**
     * 获取postcard
     *
     * @param path path
     * @return
     */
    public static Postcard getPostcard(String path) {
        return ARouter.getInstance().build(path);
    }


    /**
     * 获取携带登录标志的postcard
     *
     * @param path path
     * @return
     */
    public static Postcard getPostcardWithLoginTag(String path) {
        return getPostcard(path).withString("loginKey",TAG_LOGIN);
    }


    /**
     * 跳转
     *
     * @param path path
     * @return
     */
    public static void nav(String path) {
        getPostcard(path).navigation();
    }


}
