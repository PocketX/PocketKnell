package org.pocketx.knell.domain

/**
 * @author wd
 */
interface BirthdayManager {

    fun set(birthday: Birthday)

    fun get(): Birthday

    fun exist(): Boolean

    /**
     * 删除生日数据
     */
    fun deleteBirthday()
}
