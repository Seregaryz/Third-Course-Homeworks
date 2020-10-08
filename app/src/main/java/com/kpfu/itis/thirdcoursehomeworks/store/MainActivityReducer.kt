package com.kpfu.itis.thirdcoursehomeworks.store

class MainActivityReducer {

    fun reduce(state: MainActivityState, action: MainActivityAction): MainActivityState {
        return when (action) {
            is MainActivityAction.CountWrote -> state.copy(counts = null)
            is MainActivityAction.CalculationStarted -> state.copy(isLoading = true)
            is MainActivityAction.CountCalculationFinished -> state.copy(isLoading = false, counts = action.counts)
        }
    }
}