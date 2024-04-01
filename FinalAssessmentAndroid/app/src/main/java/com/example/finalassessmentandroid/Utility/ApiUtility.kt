package com.example.finalassessmentandroid.Utility

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtility {

    val BASE_URL = "http://172.20.10.2:8081/api/v1/"

    fun apiUtilityFun(): Retrofit {
        return Retrofit
            .Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}