package org.pocketx.knell.utils;

import android.annotation.SuppressLint;
import android.app.Application;

import org.pocketx.knell.domain.BirthdayManager;

/**
 * Created by zhangchao on 2017/10/31.
 */

public final class Injector {

    private static final String BIRTHDAY_SERVICE = "org.pocketx.knell.birthday";

    public static boolean matchService(String name){
        return BIRTHDAY_SERVICE.equals(name);
    }

    @SuppressLint("WrongConstant")
    public static BirthdayManager obtain(Application application){
        return (BirthdayManager) application.getSystemService(BIRTHDAY_SERVICE);
    }

    private Injector(){
        throw new AssertionError("No instance.");
    }

}
