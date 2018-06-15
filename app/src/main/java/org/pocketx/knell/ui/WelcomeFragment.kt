package org.pocketx.knell.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.pocketx.knell.R
import org.pocketx.knell.domain.BirthdayRepository
import java.util.concurrent.TimeUnit

class WelcomeFragment : Fragment() {

    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View = inflater.inflate(R.layout.fragment_welcome, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val birthdayRepository = BirthdayRepository.create(view.context)
        disposables.add(Observable.fromCallable({ birthdayRepository.exist() })
                .delay(1500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ openPage(it) }))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.dispose()
    }

    private fun openPage(exist: Boolean) {
        findNavController().navigate(
                if (exist) R.id.action_welcomeFragment_to_knellFragment
                else R.id.action_welcomeFragment_to_chooseDateFragment)
    }
}
