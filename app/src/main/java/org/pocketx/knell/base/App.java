package org.pocketx.knell.base;

import android.app.Application;

import org.pocketx.knell.BuildConfig;
import org.pocketx.knell.domain.BirthdayManager;
import org.pocketx.knell.domain.BirthdayManagerImpl;
import org.pocketx.knell.utils.Injector;
import org.pocketx.knell.utils.Utils;

import timber.log.Timber;

/**
 * @author Shadow
 * @date 2017/10/2215:10
 */

public final class App extends Application {

    private BirthdayManager birthdayManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        birthdayManager = BirthdayManagerImpl.create(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.i("I am Knell.");
    }

    @Override
    public Object getSystemService(String name) {
        if (Injector.matchService(name)) {
            return birthdayManager;
        }
        return super.getSystemService(name);
    }

}
