package dev.sobhy.jameya.util

data class Result<T>(
    val isSuccess: Boolean,
    val data: T? = null,
    val errorMessage: String? = null
)