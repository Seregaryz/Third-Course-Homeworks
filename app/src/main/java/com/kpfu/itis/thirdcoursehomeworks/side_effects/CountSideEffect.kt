package com.kpfu.itis.thirdcoursehomeworks.side_effects

import com.freeletics.rxredux.StateAccessor
import com.kpfu.itis.thirdcoursehomeworks.service.CalculationService
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityAction
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityState
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType

class CountSideEffect(
    private val service: CalculationService,
): MainActivitySideEffect {

    private var preCurrentIndex: Int = -1
    private var preCurrentValue: Int = 0
    private var currentIndex: Int = -1
    private var currentValue: Int = 0

    override fun invoke(
        actions: Observable<MainActivityAction>,
        state: StateAccessor<MainActivityState>
    ): Observable<out MainActivityAction> {
        return actions.ofType<MainActivityAction.CountWrote>()
            .switchMap{ action ->
                calculateAndUpdate(action.wroteCount.toInt(), action.index)
                    .map<MainActivityAction> { MainActivityAction.CountCalculationFinished(it)}
                    .toObservable()
                    .startWith(MainActivityAction.CalculationStarted)
            }
    }

    private fun calculateAndUpdate(wroteCount: Int, index: Int): Single<MutableList<String>>{
        val mCurrentValue = currentValue
        val mCurrentIndex = currentIndex
        if(index == currentIndex){
            currentValue = wroteCount
            return service.calculateValue(wroteCount, index, preCurrentValue, preCurrentIndex)
        }
        preCurrentIndex = currentIndex
        preCurrentValue = currentValue
        currentValue = wroteCount
        currentIndex = index
        return service.calculateValue(wroteCount, index, mCurrentValue, mCurrentIndex)
    }
}