package com.kpfu.itis.thirdcoursehomeworks

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentOperator = ""
    val service = CalculationService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonEqual.setOnClickListener {
            val answer = service.calculateAnswer(input_box.text.toString(), currentOperator)
            if (answer != "error") result_box.text = answer
        }
        buttonClear.setOnClickListener {
            input_box.text.clear()
            currentOperator = ""
        }
        img_button_backspace.setOnClickListener {
            if (input_box.text.isNotEmpty()) {
                if(input_box.text[input_box.text.length - 1].toString() == currentOperator){
                    currentOperator = ""
                }
                input_box.text.delete(input_box.text.length -1, input_box.text.length)
            }
        }
        buttonChangeTheme.setOnClickListener {
            val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPref?.edit()?.let { editor ->
                    Log.d("Creating", "Creating")
                    editor?.putInt(getString(R.string.theme_preferences_key), AppCompatDelegate.MODE_NIGHT_NO)
                    editor?.commit()
                }
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                with(sharedPref?.edit()) {
                    Log.d("Creating", "Creating")
                    this?.putInt(
                        getString(R.string.theme_preferences_key),
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                    this?.commit()
                }
            }
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        AppCompatDelegate.setDefaultNightMode(sharedPref.getInt(getString(R.string.theme_preferences_key), 1))
        return super.onCreateView(name, context, attrs)
    }


    fun onOperatorClick(view: View) {
        addOperation((view as Button).text.toString())
    }

    fun onNumberButtonClick(view: View) {
        addToInput((view as Button).text.toString())
    }

    private fun addToInput(value: String) {
        input_box.text.append(value)
    }

    private fun addOperation(operation: String) {
        val currInput = input_box.text
        if (currInput.endsWith("+") || currInput.endsWith("-") ||
            currInput.endsWith("x") || currInput.endsWith("/")) {
            val lastIndex = input_box.text.length - 1
            input_box.text.replaceRange(lastIndex, lastIndex, operation)
            currentOperator = operation
        } else if (currentOperator != ""){
        } else {
            addToInput(operation)
            currentOperator = operation
        }
    }

}