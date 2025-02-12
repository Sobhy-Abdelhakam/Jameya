package dev.sobhy.jameya.domain.repository

import dev.sobhy.jameya.domain.model.User

interface AuthRepository {
    suspend fun sendOtp(phoneNumber: String): Result<Unit>
    suspend fun verifyOtp(phoneNumber: String, otp: String): Result<Unit>
    suspend fun isUserLoggedIn(): Boolean
    suspend fun retrieveUser() : User?
    suspend fun refreshSession()
    suspend fun signOut()
}