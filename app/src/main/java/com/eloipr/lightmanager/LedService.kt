package com.eloipr.lightmanager

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST

interface LedService {

    @POST("/open")
    fun openLed(): Call<Void>

    @POST("/close")
    fun closeLed(): Call<Void>

    companion object {
        fun create(): LedService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.76/")
                .build()

            return retrofit.create(LedService::class.java)
        }
    }
}
