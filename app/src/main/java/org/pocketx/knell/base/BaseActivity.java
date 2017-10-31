package org.pocketx.knell.base;

import android.support.v7.app.AppCompatActivity;

import org.pocketx.knell.domain.BirthdayManager;
import org.pocketx.knell.utils.Injector;

/**
 * Created by Shadow on 2017/10/2214:16.
 */

public class BaseActivity extends AppCompatActivity {

    protected final BirthdayManager getBirthdayManager() {
        return Injector.obtain(getApplication());
    }

}
