package com.example.calculator

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

class CalcReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        println("Зашло в CalcReceiver");
        val firstNumber = intent.getStringExtra("FirstNumber")
        val secondNumber = intent.getStringExtra("SecondNumber")
        val operator = intent.getStringExtra("Operator")
        val serviceClass = CalcService::class.java
        val intent = Intent(context,  serviceClass)
        intent.putExtra("FirstNumber", firstNumber)
        intent.putExtra("SecondNumber", secondNumber)
        intent.putExtra("Operator", operator)
            context.startService(intent)
    }

}