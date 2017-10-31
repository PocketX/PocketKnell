package org.pocketx.knell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;

/**
 * Created by Shadow on 2017/10/2320:48.
 */

public class WelcomeActivity extends BaseActivity {

    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(() -> checkSP(), 1000);
    }

    /**
     * 根据SP中的值判断跳转界面
     */
    private void checkSP() {
        //判断sp中是否存储有值，如果有，跳转到时钟界面
//        String spBirthday = SPUtils.getInstance().getString(ConstantUtils.BIRTHDAY);
        Intent intent;
//        if (TextUtils.isEmpty(spBirthday)) {
        if (getBirthdayManager().exist()) {
            intent = new Intent(this, KnellActivity.class);
        } else {
            Log.d(TAG, "onCreate: sp没有值");
            intent = new Intent(this, ChooseDateActivity.class);
        }
        startActivity(intent);
        finish();
    }
}