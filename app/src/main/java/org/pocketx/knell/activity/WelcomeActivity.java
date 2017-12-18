package org.pocketx.knell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;

import timber.log.Timber;

/**
 * 欢迎界面
 *
 * @author Shadow
 * @date 2017/10/23 20:48
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(this::checkSP, 1500);
    }

    /**
     * 根据SP中的值判断跳转界面
     */
    private void checkSP() {
        //判断sp中是否存储有值，如果有，跳转到时钟界面
        Intent intent;
        if (getBirthdayManager().exist()) {
            intent = new Intent(this, KnellActivity.class);
        } else {
            Timber.d("onCreate: sp没有值");
            intent = new Intent(this, ChooseDateActivity.class);
        }
        startActivity(intent);
        finish();
    }
}