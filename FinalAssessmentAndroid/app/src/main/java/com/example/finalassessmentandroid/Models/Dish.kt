package com.example.finalassessmentandroid.Models

data class Dish(
    val id: Long,
    val name: String,
    val price: Double,
    val ingredients: String,
    val tag: String,
    val prepTime: String,
    val imageUrl: String
)
