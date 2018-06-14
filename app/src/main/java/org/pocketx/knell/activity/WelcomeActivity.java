package org.pocketx.knell.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * 欢迎界面
 *
 * @author Shadow
 * @date 2017/10/23 20:48
 */

public class WelcomeActivity extends BaseActivity {

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        disposables.add(Observable.fromCallable(this::birthdayExist)
                .delay(1500, TimeUnit.MILLISECONDS)
                .map(this::activityClass)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::openPage));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }

    private boolean birthdayExist() {
        return getBirthdayManager().exist();
    }

    private Class<? extends Activity> activityClass(boolean exist) {
        return exist ? KnellActivity.class : ChooseDateActivity.class;
    }

    private void openPage(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
        finish();
    }

}