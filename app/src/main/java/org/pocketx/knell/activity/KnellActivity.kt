package org.pocketx.knell.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_knell.*
import org.pocketx.knell.R
import org.pocketx.knell.base.BaseActivity
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit
import timber.log.Timber

/**
 * 时钟界面
 *
 * @author Shadow
 * @author shenghaiyang
 */
class KnellActivity : BaseActivity(), View.OnClickListener {

    private val mMainHandler by lazy { Handler(Looper.getMainLooper()) }
    private val mUpdateClockAction by lazy { Runnable { updateClock() } }

    private lateinit var mBirthday: LocalDateTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knell)
        clear_button.setOnClickListener(this)
        val birthday = birthdayManager.get()
        mBirthday = LocalDateTime.of(birthday.year, birthday.month, birthday.day, 0, 0, 0)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        printDog()
    }

    override fun onResume() {
        super.onResume()
        updateClock()
    }

    override fun onPause() {
        super.onPause()
        mMainHandler.removeCallbacks(mUpdateClockAction)
    }

    override fun onClick(v: View?) {
        if (v == clear_button) {
            clearBirthday()
        }
    }

    /**
     * 清除已经保存的生日数据
     */
    private fun clearBirthday() {
        birthdayManager.deleteBirthday()
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    private fun updateClock() {
        mMainHandler.postDelayed(mUpdateClockAction, 1000L)
        updateUI(mBirthday)
    }

    private fun updateUI(birthday: LocalDateTime) {
        val now = LocalDateTime.now()
        val duration = Duration.between(birthday, now)

        val days = "${duration.toDays()}\n日"
        val hours = "${duration.toHours()}\n时"
        val minutes = "${duration.toMinutes()}\n分"

        val weeks = "${duration.seconds / ChronoUnit.WEEKS.duration.seconds}\n周"
        val years = "${duration.seconds / ChronoUnit.YEARS.duration.seconds}\n年"
        val months = "${duration.seconds / ChronoUnit.MONTHS.duration.seconds}\n月"

        clock_view.setClockData(now.hour, now.minute, now.second)

        years_view.text = years
        months_view.text = months
        weeks_view.text = weeks
        days_view.text = days
        hours_view.text = hours
        minutes_view.text = minutes
    }

    private fun printDog() {
        //偶然逛知乎看到的，2333
        val dog = "dog face\n" +
                " ─────────▄──────────────▄──── \n" +
                " ─ wow ──▌▒█───────────▄▀▒▌─── \n" +
                " ────────▌▒▒▀▄───────▄▀▒▒▒▐─── \n" +
                " ───────▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐─── \n" +
                " ─────▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐─── \n" +
                " ───▄▀▒▒▒▒▒▒ such difference ─ \n" +
                " ──▐▒▒▒▄▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▒▌── \n" +
                " ──▌▒▒▐▄█▀▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐── \n" +
                " ─▐▒▒▒▒▒▒▒▒▒▒▒▌██▀▒▒▒▒▒▒▒▒▀▄▌─ \n" +
                " ─▌▒▀▄██▄▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─ \n" +
                " ─▌▀▐▄█▄█▌▄▒▀▒▒▒▒▒▒░░░░░░▒▒▒▐─ \n" +
                " ▐▒▀▐▀▐▀▒▒▄▄▒▄▒▒▒ electrons ▒▌ \n" +
                " ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒░░░░░░▒▒▒▐─ \n" +
                " ─▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─ \n" +
                " ─▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐── \n" +
                " ──▀ amaze ▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▌── \n" +
                " ────▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀─── \n" +
                " ───▐▀▒▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀───── \n" +
                " ──▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀──────── \n"
        Timber.i(dog)
    }

}