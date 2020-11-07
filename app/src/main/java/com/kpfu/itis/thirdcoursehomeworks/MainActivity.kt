package com.kpfu.itis.thirdcoursehomeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.kpfu.itis.thirdcoursehomeworks.service.CalculationService
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.FIRST_INDEX
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.SECOND_INDEX
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.THIRD_INDEX
import com.kpfu.itis.thirdcoursehomeworks.side_effects.CountSideEffect
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityAction
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityState
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityStore
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val store = MainActivityStore(listOf(CountSideEffect(CalculationService())))

    private var TAG: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        store.state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::render)
        firstNumber.doAfterTextChanged { input ->
            calculateValue(input.toString(), FIRST_INDEX)
        }
        secondNumber.doAfterTextChanged { input ->
            calculateValue(input.toString(), SECOND_INDEX)
        }
        thirdNumber.doAfterTextChanged { input ->
            calculateValue(input.toString(), THIRD_INDEX)
        }
    }

    private fun render(state: MainActivityState) {
        TAG = TAG_SYSTEM
        state.firstCount?.let {
            if (firstNumber.text.toString() != it.toString()) {
                firstNumber.setText(it.toString())
            }
        }
        state.secondCount?.let {
            if (secondNumber.text.toString() != it.toString()) {
                secondNumber.setText(it.toString())
            }
        }
        state.thirdCount?.let {
            if (thirdNumber.text.toString() != it.toString()) {
                thirdNumber.setText(it.toString())
            }
        }
        TAG = TAG_EMPTY
        progressBar.isVisible = state.isLoading
    }

    private fun calculateValue(wroteCount: String, index: Int) {
        if (TAG != TAG_SYSTEM && wroteCount != "" && wroteCount != "-") {
            store.actionRelay.onNext(MainActivityAction.CountWrote(wroteCount.toInt(), index))
        }
    }

    companion object {
        private const val TAG_SYSTEM = "automatically"
        private const val TAG_EMPTY = ""
    }

}