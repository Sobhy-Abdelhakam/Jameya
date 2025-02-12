package dev.sobhy.jameya.data.repository

import android.util.Log
import dev.sobhy.jameya.data.datastore.DataStoreManager
import dev.sobhy.jameya.data.dto.UserDto
import dev.sobhy.jameya.data.mappers.toDomain
import dev.sobhy.jameya.domain.model.User
import dev.sobhy.jameya.domain.repository.AuthRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.providers.builtin.OTP
import kotlinx.coroutines.flow.firstOrNull
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
            saveToken()
            Result.success(Unit)
        }.getOrElse {
            Result.failure(it)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return dataStoreManager.token.firstOrNull() != null
    }

    override suspend fun retrieveUser(): User? {
        val token = dataStoreManager.token.firstOrNull()
        var user: UserDto? = null
        token?.let {
            auth.retrieveUser(it).let {
                user = UserDto(
                    id = it.id,
                    fullName = it.userMetadata?.get("full_name").toString(),
                    image = it.userMetadata?.get("image_url").toString(),
                    phoneNumber = it.phone!!,
                    createdAt = it.createdAt.toString(),
                    updatedAt = it.updatedAt.toString()
                )
                Log.d("user", "User: $user")
            }
        }
        return user?.toDomain()
    }

    override suspend fun refreshSession() {
        auth.refreshCurrentSession()
        saveToken()
    }

    override suspend fun signOut() {
        auth.signOut()
        dataStoreManager.removeToken()
    }

    private suspend fun saveToken(){
        val accessToken = auth.currentAccessTokenOrNull() ?: ""
        dataStoreManager.saveToken(accessToken)
    }
}