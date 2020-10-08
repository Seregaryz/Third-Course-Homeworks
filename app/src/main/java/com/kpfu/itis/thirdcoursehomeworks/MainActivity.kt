package com.kpfu.itis.thirdcoursehomeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.kpfu.itis.thirdcoursehomeworks.service.CalculationService
import com.kpfu.itis.thirdcoursehomeworks.side_effects.ThirdCountSideEffect
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityAction
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityState
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityStore
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val store = MainActivityStore(listOf(ThirdCountSideEffect(CalculationService())))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        store.state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::render)
        firstNumber.addTextChangedListener { store.actionRelay.onNext(MainActivityAction.FirstCountWrote(it.toString())) }
        secondNumber.addTextChangedListener{ store.actionRelay.onNext(MainActivityAction.SecondCountWrote(it.toString())) }
    }

    private fun render(state: MainActivityState) {
        progressBar.isVisible = state.isLoading
        firstNumber.setText(state.firstCount)
        secondNumber.setText(state.secondCount)
        thirdNumber.setText(state.thirdCount)
    }



}