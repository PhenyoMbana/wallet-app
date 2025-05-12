package com.example.digitalwallet.models

data class Challenge(
    val title: String,
    val description: String,
    val iconResId: Int,
    val reward: Int,
    val progress: Int,
    val daysLeft: Int
)

