package com.example.digitalwallet.models

data class LeaderboardUser(
    val id: Int,
    val name: String,
    val points: Int,
    val avatarUrl: String,
    val isCurrentUser: Boolean
)

