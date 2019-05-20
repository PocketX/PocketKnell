package org.pocketx.knell.domain

import android.content.Context

/**
 * 使用sp保存生日信息
 *
 * @author zhangchao
 * @author shenghaiyang
 */
class BirthdayManagerImpl private constructor(context: Context) : BirthdayManager {

    private val preferences by lazy { context.getSharedPreferences(NAME, Context.MODE_PRIVATE) }

    override fun set(birthday: Birthday) {
        val editor = preferences.edit()
        editor.putInt(YEAR, birthday.year)
        editor.putInt(MONTH, birthday.month)
        editor.putInt(DAY, birthday.day)
        editor.apply()
    }

    override fun get(): Birthday {
        val year = preferences.getInt(YEAR, DEFAULT)
        val month = preferences.getInt(MONTH, DEFAULT)
        val day = preferences.getInt(DAY, DEFAULT)
        return Birthday(year, month, day)
    }

    override fun exist(): Boolean {
        val year = preferences.getInt(YEAR, DEFAULT)
        val month = preferences.getInt(MONTH, DEFAULT)
        val day = preferences.getInt(DAY, DEFAULT)
        return year != DEFAULT && month != DEFAULT && day != DEFAULT
    }

    override fun deleteBirthday() {
        preferences.edit().clear().apply()
    }

    companion object {

        private const val NAME = "birthday"
        private const val YEAR = "year"
        private const val MONTH = "month"
        private const val DAY = "day"
        private const val DEFAULT = -1

        fun create(context: Context): BirthdayManager {
            return BirthdayManagerImpl(context)
        }
    }
}
