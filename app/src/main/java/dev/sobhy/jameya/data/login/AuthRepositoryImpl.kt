package dev.sobhy.jameya.data.login

import dev.sobhy.jameya.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.OTP
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient
): AuthRepository {
    override suspend fun sendOtp(phoneNumber: String): Result<Unit> {
        kotlin.runCatching {
            supabase.auth.signInWith(OTP){
                this.phone = phoneNumber
            }
        }.onSuccess {
            return Result.success(Unit)
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception("Failed to send OTP"))
    }

    override suspend fun verifyOtp(phoneNumber: String, otp: String): Result<Unit> {
        kotlin.runCatching {
            supabase.auth.verifyPhoneOtp(
                type = OtpType.Phone.SMS,
                phone = phoneNumber,
                token = otp
            )
        }.onSuccess {
            return Result.success(Unit)
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception("Failed to verify OTP"))
    }
}