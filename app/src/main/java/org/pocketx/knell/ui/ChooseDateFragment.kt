package org.pocketx.knell.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_choosedate.*
import org.pocketx.knell.R
import org.pocketx.knell.domain.BirthdayRepository
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month
import timber.log.Timber

class ChooseDateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View = inflater.inflate(R.layout.fragment_choosedate, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_choose_date.setOnClickListener { chooseDate(it.context) }
    }

    private fun chooseDate(context: Context) {
        val dialog = DatePickerDialog(context, { _, year, month, dayOfMonth ->
            Timber.d("onDateSet: year = %d, month = %d, dayOfMonth = %d.",
                    year, month, dayOfMonth)
            val birthday = LocalDateTime.of(year, Month.of(month + 1), dayOfMonth,
                    0, 0, 0)
            BirthdayRepository.create(context).set(birthday)
            Toast.makeText(context, R.string.save_over, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_chooseDateFragment_to_knellFragment)

        }, 1995, 7, 19)
        dialog.show()
    }
}
