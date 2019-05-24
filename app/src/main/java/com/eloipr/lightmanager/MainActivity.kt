package com.eloipr.lightmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSeekBarState(enabled = false)

        ledState()

        led_intensity_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (i%5 == 0) {
                    setIntensity(i)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    fun openLed(view: View) {
        Log.d(TAG, "led opened")
        doAsync {
            val service = LedService.create()
            val call = service.openLed()
            call.enqueue(object : Callback<SuccessResponse> {
                override fun onFailure(call: Call<SuccessResponse>?, t: Throwable?) {
                    Log.d(TAG, t.toString())
                }

                override fun onResponse(call: Call<SuccessResponse>?, response: Response<SuccessResponse>?) {
                    setSeekBarState(enabled = true)
                    setSeekBarValue(led_intensity_seek_bar.max)
                }

            })
        }
    }

    fun closeLed(view: View) {
        Log.d(TAG, "led closed")
        doAsync {
            val service = LedService.create()
            val call = service.closeLed()
            call.enqueue(object : Callback<SuccessResponse> {
                override fun onFailure(call: Call<SuccessResponse>?, t: Throwable?) {
                    Log.d(TAG, t.toString())
                }

                override fun onResponse(call: Call<SuccessResponse>?, response: Response<SuccessResponse>?) {
                    setSeekBarState(enabled = false)
                    setSeekBarValue(0)
                }

            })
        }
    }

    private fun setIntensity(i: Int) {
        doAsync {
            val service = LedService.create()
            val call = service.setIntensity(i)
            call.enqueue(object : Callback<SuccessResponse> {
                override fun onFailure(call: Call<SuccessResponse>?, t: Throwable?) {
                    Log.d(TAG, t.toString())
                }

                override fun onResponse(call: Call<SuccessResponse>?, response: Response<SuccessResponse>?) {
                }

            })
        }
    }

    private fun ledState() {
        doAsync {
            val service = LedService.create()
            val call = service.ledState()
            call.enqueue(object : Callback<Intensity> {
                override fun onFailure(call: Call<Intensity>?, t: Throwable?) {
                    Log.d(TAG, t.toString())
                }

                override fun onResponse(call: Call<Intensity>?, response: Response<Intensity>?) {
                    initLed(response?.body()?.intensity)
                }

            })
        }
    }

    private fun setSeekBarState(enabled: Boolean) {
        led_intensity_seek_bar.isEnabled = enabled
    }

    private fun setSeekBarValue(value: Int) {
        led_intensity_seek_bar.progress = value
    }

    private fun initLed(intensity: Int?) {
        if (intensity != null) {
            setSeekBarValue(intensity)
        }
    }
}
