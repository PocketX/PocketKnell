package org.pocketx.knell.domain

import android.content.Context
import android.preference.PreferenceManager
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

const val key_birthday = "birthday"

interface BirthdayRepository {

    fun clear()

    fun exist(): Boolean

    fun set(birthday: LocalDateTime)

    fun get(): LocalDateTime

    companion object {

        fun create(context: Context) = object : BirthdayRepository {

            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

            override fun clear() {
                prefs.edit().remove(key_birthday).apply()
            }

            override fun exist() = prefs.contains(key_birthday)

            override fun set(birthday: LocalDateTime) {
                prefs.edit().putString(key_birthday, birthday.format(formatter)).apply()

            }

            override fun get() = LocalDateTime.parse(prefs.getString(key_birthday, ""), formatter)

        }

    }
}