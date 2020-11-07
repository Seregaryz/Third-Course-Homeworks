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

    override fun invoke(
        actions: Observable<MainActivityAction>,
        state: StateAccessor<MainActivityState>
    ): Observable<out MainActivityAction> {
        return actions.ofType<MainActivityAction.CountWrote>()
            .switchMap{ action ->
                calculateAndUpdate(action.wroteCount, action.index, state())
                    .map<MainActivityAction> { calculatedValues -> MainActivityAction.CalculationFinished(
                        calculatedValues[0],
                        calculatedValues[1],
                        calculatedValues[2]
                    )}
                    .toObservable()
                    .startWith(MainActivityAction.CalculationStarted)
            }
    }

    private fun calculateAndUpdate(wroteCount: Int?, wroteIndex: Int, state: MainActivityState): Single<MutableList<Int?>>{
        when (wroteIndex) {
            1 -> state.firstCount = wroteCount
            2 -> state.secondCount = wroteCount
            3 -> state.thirdCount = wroteCount
        }
        if(wroteIndex == state.lastIndex){
            return service.calculateValue(state)
        }
        state.preLastIndex = state.lastIndex
        state.lastIndex = wroteIndex
        return service.calculateValue(state)
    }
}