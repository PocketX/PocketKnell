package org.pocketx.knell.utils

import android.annotation.SuppressLint
import android.app.Application

import org.pocketx.knell.domain.BirthdayManager

/**
 *
 * @author Shadow
 * @author shenghaiyang
 */
class Injector private constructor() {

    companion object {

        private const val BIRTHDAY_SERVICE = "org.pocketx.knell.birthday"

        fun matchService(name: String): Boolean {
            return BIRTHDAY_SERVICE == name
        }

        @SuppressLint("WrongConstant")
        fun obtain(application: Application): BirthdayManager {
            return application.getSystemService(BIRTHDAY_SERVICE) as BirthdayManager
        }
    }

}
