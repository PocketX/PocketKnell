package org.pocketx.knell.domain;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangchao on 2017/10/31.
 */

public final class BirthdayManagerImpl implements BirthdayManager {

    private static final String NAME = "birthday";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    private static final int DEFAULT = -1;

    private final SharedPreferences preferences;

    private BirthdayManagerImpl(Context context) {
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static BirthdayManager create(Context context) {
        return new BirthdayManagerImpl(context);
    }

    @Override
    public void set(Birthday birthday) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(YEAR, birthday.year);
        editor.putInt(MONTH, birthday.month);
        editor.putInt(DAY, birthday.day);
        editor.apply();
    }

    @Override
    public Birthday get() {
        int year = preferences.getInt(YEAR, DEFAULT);
        int month = preferences.getInt(MONTH, DEFAULT);
        int day = preferences.getInt(DAY, DEFAULT);
        return Birthday.create(year, month, day);
    }

    @Override
    public boolean exist() {
        int year = preferences.getInt(YEAR, DEFAULT);
        int month = preferences.getInt(MONTH, DEFAULT);
        int day = preferences.getInt(DAY, DEFAULT);
        return (year != DEFAULT && month != DEFAULT && day != DEFAULT);
    }
}
