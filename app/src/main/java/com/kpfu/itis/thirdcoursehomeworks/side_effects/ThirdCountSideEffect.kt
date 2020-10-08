package com.kpfu.itis.thirdcoursehomeworks.side_effects

import com.freeletics.rxredux.StateAccessor
import com.kpfu.itis.thirdcoursehomeworks.service.CalculationService
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityAction
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityState
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType

class ThirdCountSideEffect(
    val service: CalculationService
): MainActivitySideEffect {

    val currentIndex: Int = 0
    val currentValue: Int = 0

    override fun invoke(
        actions: Observable<MainActivityAction>,
        state: StateAccessor<MainActivityState>
    ): Observable<out MainActivityAction> {
        return actions.ofType<MainActivityAction.FirstCountWrote>()
            .switchMap { action ->
                service.calculateValue(action.wroteCount.toInt(), action.index, currentValue, currentIndex)
                    .doOnSuccess{MainActivityAction.CountCalculation(it.split("_")[0].toInt(), it.split("_")[1].toInt())}
                    .toObservable()
                    .startWith(MainActivityAction.CalculationStarted)
                    .doOnError()
            }
    }
}