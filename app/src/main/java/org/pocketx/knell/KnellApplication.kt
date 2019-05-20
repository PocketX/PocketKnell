package org.pocketx.knell

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.pocketx.knell.domain.BirthdayManagerImpl
import org.pocketx.knell.utils.Injector
import timber.log.Timber

/**
 * Knell App
 *
 * @author Shadow
 * @author shenghaiyang
 */
open class KnellApplication : Application() {

    private val birthdayManager by lazy { BirthdayManagerImpl.create(this) }

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        Timber.i("I am Knell.")
    }

    override fun getSystemService(name: String): Any? {
        return if (Injector.matchService(name)) {
            birthdayManager
        } else super.getSystemService(name)
    }

}
