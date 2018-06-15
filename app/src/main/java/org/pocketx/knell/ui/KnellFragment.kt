package org.pocketx.knell.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_knell.*
import org.pocketx.knell.R
import org.pocketx.knell.domain.BirthdayRepository
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit
import timber.log.Timber
import java.util.concurrent.TimeUnit

class KnellFragment : Fragment() {

    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View = inflater.inflate(R.layout.fragment_knell, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val birthdayRepository = BirthdayRepository.create(view.context)
        clear_button.setOnClickListener {
            // 清除已经保存的生日数据
            birthdayRepository.clear()
            findNavController().navigate(R.id.action_knellFragment_to_chooseDateFragment)
        }
        val birthday = birthdayRepository.get()
        disposables.add(Observable.interval(0, 1, TimeUnit.SECONDS)
                .map { birthday }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { updateUI(it) })

        printDog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.dispose()
    }

    /**
     * 刷新时间显示
     */
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
