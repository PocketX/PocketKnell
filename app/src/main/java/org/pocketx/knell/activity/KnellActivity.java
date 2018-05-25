package org.pocketx.knell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;
import org.pocketx.knell.domain.Birthday;
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

    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knell);
        ButterKnife.bind(this);

        Birthday birthday = getBirthdayManager().get();
        LocalDateTime dateTime = LocalDateTime.of(birthday.year, birthday.month,
                birthday.day, 0, 0);
        Duration duration = Duration.between(LocalDateTime.now(), dateTime);
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long weeks = ChronoUnit.WEEKS.between(LocalDateTime.now(), dateTime);
        long years = ChronoUnit.YEARS.between(LocalDateTime.now(), dateTime);
        long months = ChronoUnit.MONTHS.between(LocalDateTime.now(), dateTime);

        Timber.d("onCreate: 年：%d 月：%d 周：%d 天数为：%d 小时数为：%d 分钟数为：%d",
                years, months, weeks, days, hours, minutes);

        tvYear.setText(String.valueOf(years));
        tvMonth.setText(String.valueOf(months));
        tvWeek.setText(String.valueOf(weeks));
        tvDay.setText(String.valueOf(days));
        tvHour.setText(String.valueOf(hours));
        tvMinute.setText(String.valueOf(minutes));
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







