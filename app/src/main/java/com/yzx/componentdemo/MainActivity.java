package com.yzx.componentdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yzx.base.arouter.ARouterNavUtils;
import com.yzx.base.arouter.ARouterPath;
import com.yzx.component_base.ServiceFactory;
import com.yzx.component_base.empty_service.NoLoginServiceImp;
import com.yzx.componentdemo.utils.ShareUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author yzx
 */
@Route(path = ARouterPath.MAIN)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RxPermissions rxPermissions = new RxPermissions(this);


        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterNavUtils.getPostcardWithLoginTag(ARouterPath.MAIN_CONTENT).navigation();

            }
        });

        findViewById(R.id.tv_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServiceFactory serviceFactory = ServiceFactory
                        .getInstance();
                if (serviceFactory.getAccountService().isLogin()) {
                    serviceFactory
                            .setAccountService(new NoLoginServiceImp());
                    Toast.makeText(MainActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    ARouterNavUtils.nav(ARouterPath.LOGIN);
                }
            }
        });

        findViewById(R.id.tv_share).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {

                rxPermissions
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    choosePic();
                                } else {
                                    Toast.makeText(MainActivity.this, "权限被拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }

    private void choosePic() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(1)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<String> pathResult = Matisse.obtainPathResult(data);
            if (pathResult == null || pathResult.size() <= 0) {
                return;
            }
            ShareUtil.showImgShare(this, pathResult.get(0));
        }
    }
}
