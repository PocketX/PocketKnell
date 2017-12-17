package org.pocketx.knell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.pocketx.knell.R;
import org.pocketx.knell.base.BaseActivity;
import org.pocketx.knell.domain.Birthday;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时钟界面
 *
 * @author Shadow
 * @date 2017/10/22 14:15
 */

public class KnellActivity extends BaseActivity {

    private static final String TAG = "KnellActivity";

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
        DateTime birDateTime = new DateTime(birthday.year, birthday.month, birthday.day, 0, 0);
        Days days = Days.daysBetween(birDateTime, DateTime.now());
        Hours hours = Hours.hoursBetween(birDateTime, DateTime.now());
        Minutes minutes = Minutes.minutesBetween(birDateTime, DateTime.now());
        Weeks weeks = Weeks.weeksBetween(birDateTime, DateTime.now());
        Years years = Years.yearsBetween(birDateTime, DateTime.now());
        Months months = Months.monthsBetween(birDateTime, DateTime.now());

        Log.d(TAG, "onCreate: 年：" + years.getYears());
        Log.d(TAG, "onCreate: 月：" + months.getMonths());
        Log.d(TAG, "onCreate: 周：" + weeks.getWeeks());
        Log.d(TAG, "onCreate: 天数为：" + days.getDays());
        Log.d(TAG, "onCreate: 小时数为：" + hours.getHours());
        Log.d(TAG, "onCreate: 分钟数为：" + minutes.getMinutes());

        tvYear.setText(years.getYears() + "");
        tvMonth.setText(months.getMonths() + "");
        tvWeek.setText(weeks.getWeeks() + "");
        tvDay.setText(days.getDays() + "");
        tvHour.setText(hours.getHours() + "");
        tvMinute.setText(minutes.getMinutes() + "");
    }


    /**
     * 清除已经保存的生日数据
     *
     * @param view
     */
    public void btnClearBirthday(View view) {
        getBirthdayManager().deleteBirthday();
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}







