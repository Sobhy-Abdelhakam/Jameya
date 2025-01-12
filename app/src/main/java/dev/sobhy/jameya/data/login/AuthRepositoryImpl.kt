package dev.sobhy.jameya.data.login

import dev.sobhy.jameya.data.datastore.DataStoreManager
import dev.sobhy.jameya.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.OTP
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient,
    private val dataStoreManager: DataStoreManager
): AuthRepository {
    override suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        return kotlin.runCatching {
            supabase.auth.signInWith(OTP){
                this.phone = phoneNumber
            }
        }.mapCatching { }
    }

    override suspend fun verifyOtp(phoneNumber: String, otp: String): Result<Unit> {
        return kotlin.runCatching {
            supabase.auth.verifyPhoneOtp(
                type = OtpType.Phone.SMS,
                phone = phoneNumber,
                token = otp
            )
            val userId = supabase.auth.currentUserOrNull()?.id ?: throw Exception("User ID not found")
            dataStoreManager.setUserId(userId)
            Result.success(Unit)
        }.getOrElse {
            Result.failure(it)
        }
    }
}