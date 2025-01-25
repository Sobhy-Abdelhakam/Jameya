package dev.sobhy.jameya.domain.repository

interface AuthRepository {
    suspend fun sendOtp(phoneNumber: String): Result<Unit>
    suspend fun verifyOtp(phoneNumber: String, otp: String): Result<Unit>
    suspend fun isUserLoggedIn(): Boolean
    suspend fun retrieveUser()
    suspend fun refreshSession()
    suspend fun signOut()
}