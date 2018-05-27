package org.pocketx.knell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;
import org.pocketx.knell.domain.Birthday;
import org.pocketx.knell.view.ClockView;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private ScheduledExecutorService mExecutorService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knell);
        ButterKnife.bind(this);


        //偶然逛知乎看到的，2333
        Timber.d("dog face\n"+ "─────────▄──────────────▄──── \n"+
        " ─ wow ──▌▒█───────────▄▀▒▌─── \n"+
        " ────────▌▒▒▀▄───────▄▀▒▒▒▐─── \n"+
        " ───────▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐─── \n"+
        " ─────▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐─── \n"+
        " ───▄▀▒▒▒▒▒▒ such difference ─ \n"+
        " ──▐▒▒▒▄▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▒▌── \n"+
        " ──▌▒▒▐▄█▀▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐── \n"+
        " ─▐▒▒▒▒▒▒▒▒▒▒▒▌██▀▒▒▒▒▒▒▒▒▀▄▌─ \n"+
        " ─▌▒▀▄██▄▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─ \n"+
        " ─▌▀▐▄█▄█▌▄▒▀▒▒▒▒▒▒░░░░░░▒▒▒▐─ \n"+
        " ▐▒▀▐▀▐▀▒▒▄▄▒▄▒▒▒ electrons ▒▌ \n"+
        " ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒░░░░░░▒▒▒▐─ \n"+
        " ─▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─ \n"+
        " ─▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐── \n"+
        " ──▀ amaze ▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▌── \n"+
        " ────▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀─── \n"+
        " ───▐▀▒▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀───── \n"+
        " ──▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀──────── \n"
				);




    }

    @Override
    protected void onStart() {
        super.onStart();
        //创建计时器来刷新文字
        mExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("Knell-scheduled").daemon(true).build());
        mExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mExecutorService.shutdownNow();
    }

    /**
     * 刷新时间显示
     */
    private void refresh() {
        //时钟显示
        LocalDateTime now = LocalDateTime.now();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mClockView.setClockData(now.getHour(), now.getMinute(),now.getSecond() );
            }
        });


        //文字显示
        Birthday birthday = getBirthdayManager().get();
        LocalDateTime dateTime = LocalDateTime.of(birthday.year, birthday.month,
                birthday.day, 0, 0);
        Duration duration = Duration.between(dateTime, LocalDateTime.now());
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long weeks = ChronoUnit.WEEKS.between(dateTime, LocalDateTime.now());
        long years = ChronoUnit.YEARS.between(dateTime, LocalDateTime.now());
        long months = ChronoUnit.MONTHS.between(dateTime, LocalDateTime.now());

        Timber.d("onCreate: 年：%d 月：%d 周：%d 天数为：%d 小时数为：%d 分钟数为：%d",
                years, months, weeks, days, hours, minutes);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mYearsView.setText(String.format("%d\n年", years));
                mMonthsView.setText(String.format("%d\n月", months));
                mWeeksView.setText(String.format("%d\n周", weeks));
                mDaysView.setText(String.format("%d\n日", days));
                mHoursView.setText(String.format("%d\n时", hours));
                mMinutesView.setText(String.format("%d\n分", minutes));
            }
        });
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







