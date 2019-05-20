package org.pocketx.knell.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import org.pocketx.knell.R
import org.pocketx.knell.base.BaseActivity


/**
 * 欢迎界面
 *
 * @author Shadow
 * @author shenghaiyang
 */
class WelcomeActivity : BaseActivity() {

    private val mMainHandler by lazy { Handler(Looper.getMainLooper()) }
    private val mRunnable by lazy { Runnable { openPage() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    override fun onResume() {
        super.onResume()
        mMainHandler.postDelayed(mRunnable, DURATION_TIME_MILLS)
    }

    override fun onPause() {
        super.onPause()
        mMainHandler.removeCallbacks(mRunnable)
    }

    private fun openPage() {
        val exist = birthdayManager.exist()
        val clazz = if (exist) KnellActivity::class.java else ChooseDateActivity::class.java
        startActivity(Intent(this, clazz))
        finish()
    }

    companion object {
        private const val DURATION_TIME_MILLS = 1500L
    }

}