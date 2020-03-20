package com.matrixalex.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var calculator: Calculator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculator = Calculator()
        valueTextView.setText("0")
        btn0.setOnClickListener{handleClick(btn0.text)}
        btn1.setOnClickListener{handleClick(btn1.text)}
        btn2.setOnClickListener{handleClick(btn2.text)}
        btn3.setOnClickListener{handleClick(btn3.text)}
        btn4.setOnClickListener{handleClick(btn4.text)}
        btn5.setOnClickListener{handleClick(btn5.text)}
        btn6.setOnClickListener{handleClick(btn6.text)}
        btn7.setOnClickListener{handleClick(btn7.text)}
        btn8.setOnClickListener{handleClick(btn8.text)}
        btn9.setOnClickListener{handleClick(btn9.text)}
        btn_point.setOnClickListener{handleClick(btn_point.text)}
        btn_equals.setOnClickListener{handleClick(btn_equals.text)}
        btn_DEL.setOnClickListener{handleClick(btn_DEL.text)}
        btn_c.setOnClickListener{handleClick(btn_c.text)}
        btn_divide.setOnClickListener{handleClick(btn_divide.text)}
        btn_invert.setOnClickListener{handleClick(btn_invert.text)}
        btn_plus.setOnClickListener{handleClick(btn_plus.text)}
        btn_minus.setOnClickListener{handleClick(btn_minus.text)}
        btn_multiple.setOnClickListener{handleClick(btn_multiple.text)}
        btn_sqrt.setOnClickListener{handleClick(btn_sqrt.text)}
    }

    private fun handleClick(btnText: CharSequence){
        val text = btnText.toString().toUpperCase()
        if (text.equals("0") || text.equals("1") || text.equals("2") || text.equals(".") ||
            text.equals("3") || text.equals("4") || text.equals("5") || text.equals("6") ||
            text.equals("7") || text.equals("8") || text.equals("9")) {
            val currentString = calculator.addChar(text)
            valueTextView.setText(currentString)
        } else if (text.equals("C")) {
            calculator.clearAll()
            valueTextView.setText("0")
        } else if (text.equals("DEL")) {
            val currentStr = calculator.deleteChar()
            valueTextView.setText(currentStr)
        } else {
            val currentStr = calculator.handleOperator(text)
            Log.d("currentStr", currentStr)
            if (calculator.is_null()){
                valueTextView.setText("NULL")
            } else {
                valueTextView.setText(currentStr)
            }
        }
    }
}
