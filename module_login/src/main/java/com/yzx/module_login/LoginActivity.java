package com.yzx.module_login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yzx.component_base.ServiceFactory;

import static com.yzx.base.arouter.ARouterPath.LOGIN;

/**
 * @author yzx
 * @date 2019/6/11
 * Description
 */
@Route(path = LOGIN)
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ServiceFactory
                .getInstance()
                .setAccountService(new LoginService());
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();

    }
}
