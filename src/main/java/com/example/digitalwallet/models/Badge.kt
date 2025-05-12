package com.example.digitalwallet.models

data class Badge(
    val title: String,
    val description: String,
    val iconResId: Int,
    val earned: Boolean,
    val date: String? = null,
    val progress: Int = 0
)

