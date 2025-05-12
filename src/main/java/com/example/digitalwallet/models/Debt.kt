package com.example.digitalwallet.models

import java.util.Date

data class Debt(
    val id: Int,
    val name: String,
    val totalAmount: Double,
    val remainingAmount: Double,
    val interestRate: Double,
    val minimumPayment: Double,
    val dueDate: Date
)

