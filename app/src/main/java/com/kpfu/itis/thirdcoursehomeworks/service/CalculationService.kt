package com.kpfu.itis.thirdcoursehomeworks.service

import com.kpfu.itis.thirdcoursehomeworks.service.Constans.FIRST_INDEX
import com.kpfu.itis.thirdcoursehomeworks.service.Constans.SECOND_INDEX
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class CalculationService {

    fun calculateValue(
        wroteCount: Int,
        wroteIndex: Int,
        currentCount: Int,
        currentIndex: Int
    ): Single<MutableList<String>> {
        var res = mutableListOf("", "", "")
        if(currentIndex == -1){
            res[wroteIndex] = wroteCount.toString()
            return Single.just(res)
        }
        when (wroteIndex) {
             FIRST_INDEX -> {
                return if (currentIndex == SECOND_INDEX) {
                    res = setCalculatedVal(wroteCount, currentCount, wroteCount + currentCount)
                    Single.just(res)
                        .delay(2, TimeUnit.SECONDS)
                } else {
                    res = setCalculatedVal(wroteCount, currentCount - wroteCount, currentCount)
                    Single.just(res)
                        .delay(2, TimeUnit.SECONDS)
                }
            }
            SECOND_INDEX -> {
                return if (currentIndex == FIRST_INDEX) {
                    res = setCalculatedVal(currentCount, wroteCount, wroteCount + currentCount)
                    Single.just(res)
                        .delay(2, TimeUnit.SECONDS)
                } else {
                    res = setCalculatedVal(currentCount - wroteCount, wroteCount, currentCount)
                    Single.just(res)
                        .delay(2, TimeUnit.SECONDS)
                }
            }
            else -> {
                return if (currentIndex == FIRST_INDEX) {
                    res = setCalculatedVal(currentCount, wroteCount - currentCount, wroteCount)
                    Single.just(res)
                        .delay(2, TimeUnit.SECONDS)
                } else {
                    res = setCalculatedVal(wroteCount - currentCount, currentCount, wroteCount)
                    Single.just(res)
                        .delay(2, TimeUnit.SECONDS)
                }
            }
        }
    }

    private fun setCalculatedVal(firstCount: Int, secondCount: Int, thirdCount: Int)
            = mutableListOf(firstCount.toString(), secondCount.toString(), thirdCount.toString())
}