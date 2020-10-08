package com.kpfu.itis.thirdcoursehomeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.kpfu.itis.thirdcoursehomeworks.service.CalculationService
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.FIRST_COUNT_INDEX
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.SECOND_COUNT_INDEX
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.THIRD_COUNT_INDEX
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
            calculateValue(input.toString(), FIRST_COUNT_INDEX)
        }
        secondNumber.doAfterTextChanged{ input ->
            calculateValue(input.toString(), SECOND_COUNT_INDEX)
        }
        thirdNumber.doAfterTextChanged { input ->
            calculateValue(input.toString(), THIRD_COUNT_INDEX)
        }
    }

    private fun render(state: MainActivityState) {
        state.counts?.let {
            TAG = TAG_SYSTEM
            if (firstNumber.text.toString() != it[0]) {
                firstNumber.setText(it[0])
            }
            if (secondNumber.text.toString() != it[1]) {
                secondNumber.setText(it[1])
            }
            if (thirdNumber.text.toString() != it[2]) {
                thirdNumber.setText(it[2])
            }
            TAG = TAG_EMPTY
        }
        progressBar.isVisible = state.isLoading
    }

    private fun calculateValue(wroteCount: String, index: Int) {
        if(TAG != TAG_SYSTEM && wroteCount != "" && wroteCount != "-"){
            store.actionRelay.onNext(MainActivityAction.CountWrote(wroteCount, index))
        }
    }

    companion object {
        private const val TAG_SYSTEM = "automatically"
        private const val TAG_EMPTY = ""
    }

}