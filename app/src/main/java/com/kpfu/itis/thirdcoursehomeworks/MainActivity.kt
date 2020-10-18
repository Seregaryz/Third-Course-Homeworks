package com.kpfu.itis.thirdcoursehomeworks

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isBtnShowDown = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initOnClickListeners()
    }

    private fun initOnClickListeners(){
        btnShow.setOnClickListener {
            if(!isBtnShowDown) {
                val upToDownAnimator =
                    AnimatorInflater.loadAnimator(this, R.animator.up_to_down_animator)
                upToDownAnimator.apply {
                    setTarget(it)
                    doOnStart {
                        val appearanceAnimation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.btn_appearance_to_visible)
                        btnSpin.startAnimation(appearanceAnimation)
                    }
                    doOnEnd {
                        btnSpin.visibility = View.VISIBLE
                        isBtnShowDown = true
                    }
                    start()
                }
            } else {
                val downToUpAnimator = AnimatorInflater.loadAnimator(this, R.animator.down_to_up_animator)
                downToUpAnimator.apply {
                    setTarget(it)
                    doOnStart {
                        val appearanceAnimation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.btn_appearance_to_invisible)
                        btnSpin.startAnimation(appearanceAnimation)
                    }
                    doOnEnd {
                        btnSpin.visibility = View.INVISIBLE
                        isBtnShowDown = false
                    }
                    start()
                }
            }
        }
        btnSpin.setOnClickListener {
            val animationRotateCenter = AnimationUtils.loadAnimation(
                this, R.anim.btn_rotation)
            it.startAnimation(animationRotateCenter)
        }
    }
}