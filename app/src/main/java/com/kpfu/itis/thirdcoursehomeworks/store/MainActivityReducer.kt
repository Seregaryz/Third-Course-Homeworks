package com.kpfu.itis.thirdcoursehomeworks.store

class MainActivityReducer {

    fun reduce(state: MainActivityState, action: MainActivityAction): MainActivityState {
        return when (action) {
            is MainActivityAction.CountWrote -> {
                state.apply {
                    when (action.index) {
                        1 -> firstCount = action.wroteCount
                        2 -> secondCount = action.wroteCount
                        3 -> thirdCount = action.wroteCount
                    }
                    if (lastIndex != action.index) {
                        preLastIndex = lastIndex
                        lastIndex = action.index
                    }
                    state.copy(
                        firstCount = firstCount,
                        secondCount = secondCount,
                        thirdCount = thirdCount,
                        lastIndex = lastIndex,
                        preLastIndex = preLastIndex
                    )
                }
            }
            is MainActivityAction.CalculationStarted -> state.copy(isLoading = true)
            is MainActivityAction.CalculationFinished -> state.copy(
                isLoading = false,
                firstCount = action.firstCount,
                secondCount = action.secondCount,
                thirdCount = action.thirdCount
            )
        }
    }
}