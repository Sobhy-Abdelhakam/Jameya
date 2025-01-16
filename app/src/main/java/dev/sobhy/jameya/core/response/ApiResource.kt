package dev.sobhy.jameya.core.response

sealed class ApiResource<out T> {
    object Loading: ApiResource<Nothing>()
    data class Success<out T>(val data: T): ApiResource<T>()
    data class Error(
        val isNetworkError: Boolean?,
        val errorCode: Int?,
        val errorBody: String?
    ): ApiResource<Nothing>()
}