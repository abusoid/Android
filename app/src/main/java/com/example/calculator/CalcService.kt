package com.example.calculator
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast


class CalcService : Service() {
    val INTENT_SEND_RESULT = "com.example.calculator.CalcResult"
    private val myBinder = MyLocalBinder()
    override fun onBind(intent: Intent?): MyLocalBinder {
        //println(intent.EXTRA_TEXT)
        println("OnBind")
        return myBinder
    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        println("Service started.")
        println(intent.getStringExtra("Expression"));
        val firstNumber:String? = intent.getStringExtra("FirstNumber")
        println(firstNumber)
        val secondNumber:String? = intent.getStringExtra("SecondNumber")
        println(secondNumber)
        val operator:String? = intent.getStringExtra("Operator")
        println(operator)
        var result = ""
        when (operator) {
            "+" -> result = (firstNumber!!.toFloat() + secondNumber!!.toFloat()).toString()
            "-" -> result = (firstNumber!!.toFloat() - secondNumber!!.toFloat()).toString()
            "x" -> result = (firstNumber!!.toFloat() * secondNumber!!.toFloat()).toString()
            "/" -> {
                //checkZero(secondNumber)
                if (secondNumber == "0.0") {
                    val divideZeroError = "На ноль делить нельзя!"
                    val duration = Toast.LENGTH_SHORT
                    Toast.makeText(applicationContext, divideZeroError, duration).show()
                } else {
                    result = (firstNumber!!.toFloat() / secondNumber!!.toFloat()).toString()
                }
            }
            else -> {
                val nullOperator = "Выберите оператор"
                val duration = Toast.LENGTH_SHORT
                Toast.makeText(applicationContext, nullOperator, duration).show()
            }
        }
        println("Создание обратного интента")
        val intentResult = Intent()
        intentResult.action = INTENT_SEND_RESULT
        println("intentResult.action : " + intentResult.action)
        intentResult.putExtra("Result", result)
        println("result: $result")
        intentResult.setPackage(packageName)
        println("packageName: $packageName")
        sendBroadcast(intent)

        return START_STICKY
    }
    inner class MyLocalBinder : Binder() {
        fun getService() : CalcService {
            return this@CalcService
        }
    }
}