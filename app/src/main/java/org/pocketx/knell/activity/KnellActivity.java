package org.pocketx.knell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;
import org.pocketx.knell.domain.Birthday;
import org.pocketx.knell.view.ClockView;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.ChronoUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * 时钟界面
 *
 * @author Shadow
 * @author shenghaiyang
 * @date 2017/10/22 14:15
 */

public class KnellActivity extends BaseActivity {

    @BindView(R.id.years)
    TextView mYearsView;
    @BindView(R.id.months)
    TextView mMonthsView;
    @BindView(R.id.weeks)
    TextView mWeeksView;
    @BindView(R.id.days)
    TextView mDaysView;
    @BindView(R.id.hours)
    TextView mHoursView;
    @BindView(R.id.minutes)
    TextView mMinutesView;
    @BindView(R.id.clock_view)
    ClockView mClockView;

    private final Handler mMainHandler = new Handler(Looper.getMainLooper());

    private final Runnable mTicker = new Runnable() {
        public void run() {
            onTimeChanged();

            long now = SystemClock.uptimeMillis();
            long next = now + (1000 - now % 1000);

            mMainHandler.postAtTime(mTicker, next);
        }
    };

    private LocalDateTime mBirthdayDateTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knell);
        ButterKnife.bind(this);
        Birthday birthday = getBirthdayManager().get();
        mBirthdayDateTime = LocalDateTime.of(birthday.year, birthday.month,
                birthday.day, 0, 0);

        //偶然逛知乎看到的，2333
        Timber.d("dog face\n" + "─────────▄──────────────▄──── \n" +
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
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTicker.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainHandler.removeCallbacks(mTicker);
    }

    /**
     * 刷新时间显示
     */
    private void onTimeChanged() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(mBirthdayDateTime, now);
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        mBirthdayDateTime.until(now, ChronoUnit.WEEKS);

        long weeks = duration.getSeconds() / ChronoUnit.WEEKS.getDuration().getSeconds();
        long years = duration.getSeconds() / ChronoUnit.YEARS.getDuration().getSeconds();
        long months = duration.getSeconds() / ChronoUnit.MONTHS.getDuration().getSeconds();

        mClockView.setClockData(now.getHour(), now.getMinute(), now.getSecond());
        mYearsView.setText(String.format("%d\n年", years));
        mMonthsView.setText(String.format("%d\n月", months));
        mWeeksView.setText(String.format("%d\n周", weeks));
        mDaysView.setText(String.format("%d\n日", days));
        mHoursView.setText(String.format("%d\n时", hours));
        mMinutesView.setText(String.format("%d\n分", minutes));
//        Timber.d("onCreate: 年：%d 月：%d 周：%d 天数为：%d 小时数为：%d 分钟数为：%d",
//                years, months, weeks, days, hours, minutes);
    }

    /**
     * 清除已经保存的生日数据
     */
    @OnClick(R.id.clear_button)
    void clearBirthday() {
        getBirthdayManager().deleteBirthday();
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

}







