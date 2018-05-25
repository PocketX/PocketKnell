package org.pocketx.knell.base;

import android.support.v7.app.AppCompatActivity;

import org.pocketx.knell.domain.BirthdayManager;
import org.pocketx.knell.utils.Injector;

/**
 * @author Shadow
 * @author shenghaiyang
 * @date 2017/10/22 14:16
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected final BirthdayManager getBirthdayManager() {
        return Injector.obtain(getApplication());
    }

}
