package org.pocketx.knell.base

import androidx.appcompat.app.AppCompatActivity

import org.pocketx.knell.domain.BirthdayManager
import org.pocketx.knell.utils.Injector

/**
 * Activity基类
 *
 * @author Shadow
 * @author shenghaiyang
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val birthdayManager: BirthdayManager
        get() = Injector.obtain(application)

}
