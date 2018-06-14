package org.pocketx.knell;

import org.pocketx.knell.base.KnellApp;

import timber.log.Timber;

public class KnellDebugApp extends KnellApp {

    @Override
    public void onCreate() {
        Timber.plant(new Timber.DebugTree());
        super.onCreate();
    }
}
