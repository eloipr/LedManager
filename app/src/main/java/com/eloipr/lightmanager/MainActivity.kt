package com.eloipr.lightmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

abstract class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openLed(view: View) {
        Log.d(TAG, "led opened")
        sendPostRequest("open")
    }

    fun closeLed(view: View) {
        Log.d(TAG, "led closed")
        sendPostRequest("close")
    }

    private fun sendPostRequest(method: String) {
        val service = LedService()
        val apiController = APIController(service)

        apiController.post(method) { response ->
            Log.d(TAG, response)
        }
    }
}
