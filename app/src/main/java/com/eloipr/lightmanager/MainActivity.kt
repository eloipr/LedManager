package com.eloipr.lightmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

//import org.jetbrains.anko.doAsync
//import org.jetbrains.anko.toast
//import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openLed(view: View) {
        Log.d(TAG, "led opened")

        doAsync {
            val service = LedService.create()
            val call = service.openLed()
            call.execute()
        }
    }

    fun closeLed(view: View) {
        Log.d(TAG, "led closed")

        doAsync {
            val service = LedService.create()
            val call = service.closeLed()
            call.execute()
        }
    }
}
