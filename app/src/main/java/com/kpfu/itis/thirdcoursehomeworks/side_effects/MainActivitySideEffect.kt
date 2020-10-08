package com.kpfu.itis.thirdcoursehomeworks.side_effects

import com.freeletics.rxredux.SideEffect
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityAction
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityState

typealias MainActivitySideEffect = SideEffect<MainActivityState, MainActivityAction>