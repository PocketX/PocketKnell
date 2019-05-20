package org.pocketx.knell.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choose_date.*
import org.pocketx.knell.R
import org.pocketx.knell.base.BaseActivity
import org.pocketx.knell.domain.Birthday
import timber.log.Timber

/**
 * 选择时间
 *
 * @author Shadow
 * @author shenghaiyang
 */
class ChooseDateActivity : BaseActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_date)
        btn_choose_date.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == btn_choose_date) {
            chooseDate()
        }
    }

    private fun chooseDate() {
        val dialog = DatePickerDialog(this, { view1, year, month, dayOfMonth ->
            Timber.d(
                "onDateSet: year = %d, month = %d, dayOfMonth = %d.",
                year, month, dayOfMonth
            )
            birthdayManager.set(Birthday(year, month + 1, dayOfMonth))
            Toast.makeText(this@ChooseDateActivity, R.string.save_over, Toast.LENGTH_SHORT)
                .show()
            startActivity(Intent(this@ChooseDateActivity, KnellActivity::class.java))
            finish()
        }, 1995, 7, 19)
        dialog.show()
    }
}