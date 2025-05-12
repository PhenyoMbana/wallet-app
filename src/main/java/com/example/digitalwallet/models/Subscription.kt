package com.example.digitalwallet.models

import java.util.Date

data class Subscription(
    val id: Int,
    val name: String,
    val amount: Double,
    val cycle: String,
    val nextPayment: Date,
    val iconName: String,
    val colorHex: String
)

