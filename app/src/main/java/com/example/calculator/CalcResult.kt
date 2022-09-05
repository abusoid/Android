package com.example.calculator

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity


class CalcResult : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        println("Зашло в CalcResult");
        val result = intent.getStringExtra("Result")
        //val intentActivity = Intent()
        //intentActivity.action = "android.intent.action.MAIN"

        val intentActivity = Intent(context, MainActivity::class.java)
        intentActivity.putExtra("Result", result)
        println("result: " + result)
        println("Конец CalcResult")
        //startActivity(intentActivity)
    }
}