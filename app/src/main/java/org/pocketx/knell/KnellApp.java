package org.pocketx.knell;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.leakcanary.LeakCanary;

import org.pocketx.knell.BuildConfig;
import org.pocketx.knell.domain.BirthdayManager;
import org.pocketx.knell.domain.BirthdayManagerImpl;
import org.pocketx.knell.utils.Injector;
import org.pocketx.knell.utils.Utils;

import timber.log.Timber;

/**
 * @author Shadow
 * @author shenghaiyang
 * @date 2017/10/22 15:10
 */
public class KnellApp extends Application {

    private BirthdayManager birthdayManager;

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidThreeTen.init(this);
        Timber.i("I am Knell.");
        Utils.init(this);
        birthdayManager = BirthdayManagerImpl.create(this);
    }

    @Override
    public Object getSystemService(String name) {
        if (Injector.matchService(name)) {
            return birthdayManager;
        }
        return super.getSystemService(name);
    }

}
