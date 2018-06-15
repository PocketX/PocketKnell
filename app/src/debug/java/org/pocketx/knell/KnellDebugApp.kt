package org.pocketx.knell

import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class KnellDebugApp : KnellApp() {

    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}
