package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var editTextFirstNumber: EditText
    private lateinit var editTextSecondNumber: EditText
    private lateinit var textViewOperator: TextView
    private lateinit var textViewResult: TextView
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonEquals: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var firstNumber : Float
        var secondNumber : Float
        var toastText : String
        editTextFirstNumber = findViewById(R.id.editTextFirstNumber)
        editTextSecondNumber = findViewById(R.id.editTextSecondNumber)
        textViewOperator = findViewById(R.id.textViewOperator)
        textViewResult = findViewById(R.id.textViewResult)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonEquals = findViewById(R.id.buttonEquals)

        buttonPlus.setOnClickListener {
            textViewOperator.text = "+"
        }
        buttonMinus.setOnClickListener{
            textViewOperator.text = "-"
        }
        buttonMultiply.setOnClickListener{
            textViewOperator.text = "x"
        }
        buttonDivide.setOnClickListener{
            textViewOperator.text = "/"
        }
        buttonEquals.setOnClickListener {
            firstNumber = editTextFirstNumber.text.toString().toFloat()
            secondNumber = editTextSecondNumber.text.toString().toFloat()
            val duration = Toast.LENGTH_SHORT
            if (firstNumber == Float.NaN) {
                toastText = "Введите первое число"
                Toast.makeText(applicationContext, toastText, duration).show()
            } else if (secondNumber == Float.NaN) {
                toastText = "Введите второе число"
                Toast.makeText(applicationContext, toastText, duration).show()
            } else {
                when (textViewOperator.text) {
                    "+" -> textViewResult.text = (firstNumber + secondNumber).toString()
                    "-" -> textViewResult.text = (firstNumber - secondNumber).toString()
                    "x" -> textViewResult.text = (firstNumber * secondNumber).toString()
                    "/" -> {
                        if (secondNumber == 0.0 as Float) {
                            val divideZeroError = "На ноль делить нельзя!"
                            val duration = Toast.LENGTH_SHORT
                            Toast.makeText(applicationContext, divideZeroError, duration).show()
                        } else {
                            textViewResult.setText((firstNumber / secondNumber).toString())
                        }
                        /* try {
                        textViewResult.text = (firstNumber / secondNumber).toString()
                    } catch (e: Exception) {
                        val divideZeroError = "На ноль делить нельзя!"
                        val duration = Toast.LENGTH_SHORT
                        Toast.makeText(applicationContext, divideZeroError, duration).show()
                    }*/
                    }
                    else -> {
                        val nullOperator = "Выберите оператор"
                        val duration = Toast.LENGTH_SHORT
                        Toast.makeText(applicationContext, nullOperator, duration).show()
                    }
                }
            }
        }

    }
}

