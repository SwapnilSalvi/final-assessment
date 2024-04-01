package com.example.finalassessmentandroid.Controllers

import com.example.finalassessmentandroid.Models.DishDetailResponse
import com.example.finalassessmentandroid.Models.DishListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DishController {

    @GET("dish/getAll")
    suspend fun getAllDish(): Response<DishListResponse>

    @GET("dish/{id}")
    suspend fun getDishById(@Path("id") id:Long): Response<DishDetailResponse>
}