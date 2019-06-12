package com.yzx.componentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yzx.base.arouter.ARouterPath;

/**
 * @author yzx
 * @date 2019/6/11
 * Description 内容页面
 */
@Route(path = ARouterPath.MAIN_CONTENT)
public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
    }
}
