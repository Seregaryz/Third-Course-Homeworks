package com.kpfu.itis.thirdcoursehomeworks.store

sealed class MainActivityAction {
    class FirstCountWrote(val wroteCount: String): MainActivityAction(){
        val index: Int = 1
    }
    class CountCalculation(val index: Int, val value: Int): MainActivityAction()
    class SecondCountWrote(val wroteCount: String): MainActivityAction()
    class ThirdCountWrote(val wroteCount: String, val secondCount: String): MainActivityAction()
    class CalculationFinished(val calculatedValue: String, val position: Int): MainActivityAction()
    object CalculationStarted: MainActivityAction()
}