package org.pocketx.knell.domain

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.LocalDateTime

@RunWith(AndroidJUnit4::class)
class BirthdayRepositoryTest {

    @Test
    fun clear_test() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val birthdayRepository = BirthdayRepository.create(appContext)
        birthdayRepository.clear()
        assertFalse(birthdayRepository.exist())
        val birthday = LocalDateTime.now()
        birthdayRepository.set(birthday)
        assertTrue(birthdayRepository.exist())
        birthdayRepository.clear()
        assertFalse(birthdayRepository.exist())
    }

}