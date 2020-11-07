package com.kpfu.itis.thirdcoursehomeworks.service

import com.kpfu.itis.thirdcoursehomeworks.service.Constans.FIRST_INDEX
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.SECOND_INDEX
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.THIRD_INDEX
import com.kpfu.itis.thirdcoursehomeworks.store.MainActivityState
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class CalculationService {

    fun calculateValue(state: MainActivityState): Single<MutableList<Int?>> {
        var res = mutableListOf<Int?>()
        state.apply {
            when (lastIndex) {
                FIRST_INDEX -> {
                    res = if (preLastIndex == 2) {
                        val thirdCount = firstCount?.plus(secondCount ?: 0)
                        setCalculatedVal(firstCount, secondCount, thirdCount)
                    } else {
                        val secondCount = thirdCount?.minus(firstCount ?: 0)
                        setCalculatedVal(firstCount, secondCount, thirdCount)
                    }
                }
                SECOND_INDEX -> {
                    res = if (preLastIndex == 1) {
                        val thirdCount = firstCount?.plus(secondCount ?: 0)
                        setCalculatedVal(firstCount, secondCount, thirdCount)
                    } else {
                        val firstCount = thirdCount?.minus(secondCount ?: 0)
                        setCalculatedVal(firstCount, secondCount, thirdCount)
                    }
                }
                THIRD_INDEX -> {
                    res = if (preLastIndex == 1) {
                        val secondCount = thirdCount?.minus(firstCount ?: 0)
                        setCalculatedVal(firstCount, secondCount, thirdCount)
                    } else {
                        val firstCount = thirdCount?.minus(secondCount ?: 0)
                        setCalculatedVal(firstCount, secondCount, thirdCount)
                    }
                }
            }
        }
        return Single.just(res)
            .delay(2, TimeUnit.SECONDS)
    }

    private fun setCalculatedVal(firstCount: Int?, secondCount: Int?, thirdCount: Int?)
            = mutableListOf(firstCount, secondCount, thirdCount)
}