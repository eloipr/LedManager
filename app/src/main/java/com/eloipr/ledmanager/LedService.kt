package com.eloipr.ledmanager

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface LedService {

    @GET("/state")
    fun ledState(): Call<Intensity>

    @POST("/open")
    fun openLed(): Call<SuccessResponse>

    @POST("/close")
    fun closeLed(): Call<SuccessResponse>

    @FormUrlEncoded
    @POST("/intensity")
    fun setIntensity(@Field("intensity") intensity: Int): Call<SuccessResponse>

    companion object {
        fun create(): LedService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.76/")
                .build()

            return retrofit.create(LedService::class.java)
        }
    }
}
