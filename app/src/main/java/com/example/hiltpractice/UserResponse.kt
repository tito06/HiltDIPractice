package com.example.hiltpractice

data class UserResponse(
    val data: List<UserData>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

data class UserData(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)

data class Support(
    val text: String,
    val url: String
)