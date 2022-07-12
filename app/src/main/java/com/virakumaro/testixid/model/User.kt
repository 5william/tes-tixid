package com.virakumaro.testixid.model

import java.text.SimpleDateFormat
import java.util.*

data class User(
    val id: Long,
    val avatar_url: String,
    val email: String?,
    val html_url: String,
    val login: String,
    val created_at: String,
    val updated_at: String
)