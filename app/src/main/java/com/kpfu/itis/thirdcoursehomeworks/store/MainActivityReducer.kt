package com.kpfu.itis.thirdcoursehomeworks.store

class MainActivityReducer {

    fun reduce(state: MainActivityState, action: MainActivityAction): MainActivityState {
        return when (action) {
            is MainActivityAction.FirstCountWrote -> state.copy(isLoading = true)
            is MainActivityAction.SecondCountWrote -> state.copy(isLoading = true)
            is MainActivityAction.ThirdCountWrote -> state.copy(isLoading = true)
            MainActivityAction.CalculationFinished -> state.copy(isLoading = false)
        }
    }
}