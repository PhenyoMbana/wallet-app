package com.example.digitalwallet.utils

import java.text.NumberFormat
import java.util.Locale

/**
 * Format a number as ZAR currency
 */
fun formatCurrency(amount: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "ZA"))
    return format.format(amount)
}

