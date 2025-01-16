package dev.sobhy.jameya.domain.repository

import dev.sobhy.jameya.core.response.ApiResource
import dev.sobhy.jameya.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUser(): Flow<ApiResource<User>>
    suspend fun updateImage(image: ByteArray): ApiResource<Unit>
    suspend fun updateName(name: String): ApiResource<Unit>
}