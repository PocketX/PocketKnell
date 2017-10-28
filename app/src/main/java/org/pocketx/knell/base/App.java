package org.pocketx.knell.base;

import android.app.Application;

import org.pocketx.knell.utils.Utils;

/**
 * Created by Shadow on 2017/10/2215:10.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
    }

}
