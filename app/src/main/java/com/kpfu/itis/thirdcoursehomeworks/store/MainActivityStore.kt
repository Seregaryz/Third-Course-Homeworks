package com.kpfu.itis.thirdcoursehomeworks.store

import com.freeletics.rxredux.reduxStore
import com.kpfu.itis.thirdcoursehomeworks.side_effects.MainActivitySideEffect
import io.reactivex.subjects.PublishSubject

class MainActivityStore(
    sideEffects: List<MainActivitySideEffect>,
) {

    val actionRelay = PublishSubject.create<MainActivityAction>()

    val state = actionRelay.reduxStore(
        MainActivityState(),
        sideEffects,
        MainActivityReducer()::reduce
    )
}
