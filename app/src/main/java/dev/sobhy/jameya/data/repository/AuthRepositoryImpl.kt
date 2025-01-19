package dev.sobhy.jameya.data.repository

import dev.sobhy.jameya.data.datastore.DataStoreManager
import dev.sobhy.jameya.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.OTP
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: Auth,
    private val dataStoreManager: DataStoreManager
): AuthRepository {
    override suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        return runCatching {
            auth.signInWith(OTP){
                this.phone = phoneNumber
            }
        }.mapCatching { }
    }

    override suspend fun verifyOtp(phoneNumber: String, otp: String): Result<Unit> {
        return runCatching {
            auth.verifyPhoneOtp(
                type = OtpType.Phone.SMS,
                phone = phoneNumber,
                token = otp
            )
            val userId = auth.currentUserOrNull()?.id ?: throw Exception("User ID not found")
            dataStoreManager.setUserId(userId)
            Result.success(Unit)
        }.getOrElse {
            Result.failure(it)
        }
    }
}