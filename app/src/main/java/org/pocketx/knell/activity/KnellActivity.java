package org.pocketx.knell.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;
import org.pocketx.knell.utils.ConstantUtils;
import org.pocketx.knell.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时钟界面
 * Created by Shadow on 2017/10/2214:15.
 */

public class KnellActivity extends BaseActivity {

    private static final String TAG = "KnellActivity";

    @BindView(R.id.tv_age)
    TextView tvAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knell);
        ButterKnife.bind(this);
        String spBirthday = SPUtils.getInstance().getString(ConstantUtils.BIRTHDAY);
        Log.d(TAG, "onCreate: sp = " + spBirthday);
        String[] split = spBirthday.split("-");
        String year = split[0];
        String month = split[1];
        String dayOfMonth = split[2];
        Log.d(TAG, "onCreate: " + year + month + dayOfMonth);
    }
}