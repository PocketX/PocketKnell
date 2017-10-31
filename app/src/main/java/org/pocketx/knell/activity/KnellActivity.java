package org.pocketx.knell.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by Shadow on 2017/10/2214:15.
 */

public class KnellActivity extends BaseActivity {

    private static final String TAG = "KnellActivity";

    @BindView(R.id.tv_age)
    TextView tvAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knell);
        ButterKnife.bind(this);
        //获取sp中存储的生日
//        String spBirthday = SPUtils.getInstance().getString(ConstantUtils.BIRTHDAY);
//        Log.d(TAG, "onCreate: sp = " + spBirthday);
//        String[] split = spBirthday.split("-");
//        String spYear = split[0];
//        String spMonth = split[1];
//        String spDayOfMonth = split[2];
//        Log.d(TAG, "onCreate: " + spYear + spMonth + spDayOfMonth);

        /*
          需求：demand
          算出生日距离现在的 年、月、日、时、分
         */
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
//        calendar.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(dayOfMonth));
//        long timeInMillis = calendar.getTimeInMillis();
//        long timeCur = System.currentTimeMillis();
//        long tempTime = timeCur - timeInMillis;
//        long day = TimeUnit.MILLISECONDS.toDays(tempTime);
//        Log.d(TAG, "onCreate: 天数为： " + day);
//        Log.d(TAG, "onCreate: 天数为： " + TimeUnit.MILLISECONDS.toHours(tempTime));


        Birthday birthday = getBirthdayManager().get();
        DateTime birDateTime = new DateTime(birthday.year, birthday.month, birthday.day, 0, 0);
        Days days = Days.daysBetween(birDateTime, DateTime.now());
        Hours hours = Hours.hoursBetween(birDateTime, DateTime.now());
        Minutes minutes = Minutes.minutesBetween(birDateTime, DateTime.now());
        Weeks weeks = Weeks.weeksBetween(birDateTime, DateTime.now());
        Years years = Years.yearsBetween(birDateTime, DateTime.now());
        Months months = Months.monthsBetween(birDateTime, DateTime.now());

//        DateTime dateTime = new DateTime(Integer.parseInt(spYear),Integer.parseInt(spMonth) + 1,Integer.parseInt(spDayOfMonth),
//                0,0,0);
//        Days days = Days.daysBetween(dateTime, DateTime.now());
//        int day = days.getDays();
//        Log.d(TAG, "onCreate: 天数为：" + day);
//        int hour = day * 24 + DateTime.now().getHourOfDay();
//        Log.d(TAG, "onCreate: 小时数为：" + hour);
//        int minute = hour * 60 + DateTime.now().getMinuteOfHour();
//        Log.d(TAG, "onCreate: 分钟数为：" + minute);
//        int week = day / 7;
//        Log.d(TAG, "onCreate: 周：" + week);
//        int year = DateTime.now().getYear() - Integer.parseInt(spYear);
//        Log.d(TAG, "onCreate: 年：" + year);
//        int month = year * 12 + DateTime.now().getMonthOfYear() - Integer.parseInt(spMonth) - 1;
//        Log.d(TAG, "onCreate: 月：" + month);
    }
}







