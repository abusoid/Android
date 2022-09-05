package com.example.calculator

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
    val INTENT_SEND_TO_CALC = "com.example.calculator.CalcService"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, CalcService::class.java))
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


            val duration = Toast.LENGTH_SHORT
            firstNumber = 0.0f;
            secondNumber = 0.0f;
            println("isValid: " + isValid(editTextFirstNumber.text.toString()))
            if(isValid(editTextFirstNumber.text.toString())) {
                firstNumber = editTextFirstNumber.text.toString().toFloat()
            } else {
                toastText = "Введите первое число"
                println("Перед показом тоста 1")
                Toast.makeText(applicationContext, toastText, duration).show()
            }
            println("isValid: " + isValid(editTextSecondNumber.text.toString()))
            if(isValid(editTextSecondNumber.text.toString())) {
                secondNumber = editTextSecondNumber.text.toString().toFloat()
            } else {
                toastText = "Введите второе число"
                println("Перед показом тоста 2")
                Toast.makeText(applicationContext, toastText, duration).show()
            }
//com.example.calculator.CalcService


            println("Перед интентом")
                val intent = Intent()
                intent.action = INTENT_SEND_TO_CALC
                intent.putExtra("FirstNumber", firstNumber.toString())
                intent.putExtra("SecondNumber", secondNumber.toString())
                intent.putExtra("Operator", textViewOperator.text)
                intent.setPackage( getPackageName() )
                println("getPackageName: " + getPackageName())
                sendBroadcast(intent)


/*  val serviceClass = CalcService::class.java
  val intent = Intent(this,  serviceClass)
  if (!isServiceRunning(serviceClass)) {
      startService(intent.putExtra("Expression", firstNumber.toString() + textViewOperator.text + secondNumber.toString()))
  } else {
      toast("Service already running.")
  }*/
  /*
      when (textViewOperator.text) {
          "+"> textViewResult.text = (firstNumber + secondNumber).toString()
          "-" -> textViewResult.text = (firstNumber - secondNumber).toString()
          "x" -> textViewResult.text = (firstNumber * secondNumber).toString()
          "/" -> {
              //checkZero(secondNumber)
              if (isValid(editTextSecondNumber.text.toString()) || secondNumber == 0.0f) {
                  val divideZeroError = "На ноль делить нельзя!"
                  val duration = Toast.LENGTH_SHORT
                  Toast.makeText(applicationContext, divideZeroError, duration).show()
              } else {
                  textViewResult.setText((firstNumber / secondNumber).toString())
              }
          }
          else -> {
              val nullOperator = "Выберите оператор"
              val duration = Toast.LENGTH_SHORT
              Toast.makeText(applicationContext, nullOperator, duration).show()
          }
  }*/
}

}
fun Context.toast(message: String) {
Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}
private fun isServiceRunning(serviceClass: Class<*>): Boolean {
val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

// Loop through the running services
for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
  if (serviceClass.name == service.service.className) {
      // If the service is running then return true
      return true
  }
}
return false
}
fun isValid(text: String): Boolean {
val firstLetter = text.firstOrNull();
if(firstLetter == null) {
  return false
}
return true
}
fun checkZero(secondNumber: Float?) {
println(secondNumber)
println(secondNumber ?: "Выражение срабатывает")
secondNumber ?: throw NumberFormatException("На ноль делить нельзя")
}
fun checkSecondNumber(editTextSecondNumber: Editable?) {
println(editTextSecondNumber)
println(editTextSecondNumber ?: "Выражение срабатывает")
editTextSecondNumber ?: throw NumberFormatException("Введите второе значение")
}
fun checkFirstNumber(editTextFirstNumber: Editable?) {
println(editTextFirstNumber)
println(editTextFirstNumber ?: "Выражение срабатывает")
editTextFirstNumber ?: throw NumberFormatException("Введите второе значение")
}
}

