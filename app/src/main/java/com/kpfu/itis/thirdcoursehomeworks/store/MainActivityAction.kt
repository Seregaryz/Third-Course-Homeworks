package com.kpfu.itis.thirdcoursehomeworks.store

sealed class MainActivityAction {
    class CountWrote(val wroteCount: String, val index: Int): MainActivityAction()
    class CountCalculationFinished(val counts: MutableList<String>): MainActivityAction()
    object CalculationStarted: MainActivityAction()
}