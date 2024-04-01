package com.example.finalassessmentandroid.Models

data class DishListResponse(
    val message: String,
    val payload: List<Dish>,
    val status: String
)