package dev.sobhy.jameya.data.repository

import coil3.network.HttpException
import dev.sobhy.jameya.BuildConfig
import dev.sobhy.jameya.core.response.ApiResource
import dev.sobhy.jameya.data.datastore.DataStoreManager
import dev.sobhy.jameya.data.dto.UserDto
import dev.sobhy.jameya.data.mappers.toDomain
import dev.sobhy.jameya.domain.model.User
import dev.sobhy.jameya.domain.repository.ProfileRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage,
    private val dataStoreManager: DataStoreManager,
) : ProfileRepository {
    companion object {
        private const val USERS_TABLE = "users"
        private const val USERS_BUCKET = "Users"
        private const val IMAGE_COLUMN = "Image"
        private const val NAME_COLUMN = "full_name"
        private const val ID_COLUMN = "id"
    }

    override fun getUser(): Flow<ApiResource<User>> = flow {
        emit(ApiResource.Loading)
        val userId = dataStoreManager.userId.firstOrNull()
        if (userId.isNullOrBlank()) {
            emit(ApiResource.Error(false, null, "UserNotFound"))
            return@flow
        }
        val response = runCatching {
            postgrest.from(USERS_TABLE)
                .select { filter { eq(ID_COLUMN, userId) } }
                .decodeSingleOrNull<UserDto>()
        }.getOrNull()
        if (response == null) {
            emit(ApiResource.Error(false, null, "Failed to fetch user data"))
            return@flow
        }
        emit(ApiResource.Success(response.toDomain()))
    }.catch { handleThrowable(it) }

    override suspend fun updateImage(image: ByteArray): ApiResource<Unit> {
        return runCatching {
            val imageFileName = "profile_${System.currentTimeMillis()}.png"
            val imageUrl = uploadImage(imageFileName, image)
            updateUserData(IMAGE_COLUMN, buildImageUrl(imageUrl))
            ApiResource.Success(Unit)
        }.getOrElse { handleThrowable(it) }
    }

    override suspend fun updateName(name: String): ApiResource<Unit> {
        return runCatching {
            updateUserData(NAME_COLUMN, name)
            ApiResource.Success(Unit)
        }.getOrElse { handleThrowable(it) }
    }

    private suspend fun updateUserData(column: String, value: Any) {
        val userId = dataStoreManager.userId.firstOrNull()
        if (userId.isNullOrBlank()) throw IllegalStateException("User ID is missing")

        postgrest.from(USERS_TABLE).update(
            update = { set(column, value) },
            request = { filter { eq(ID_COLUMN, userId) } }
        )
    }

    private suspend fun uploadImage(fileName: String, image: ByteArray): String {
        return storage.from(USERS_BUCKET).upload(
            path = fileName,
            data = image,
        ) { upsert = true }.path
    }

    private fun buildImageUrl(imageFileName: String) =
        "${BuildConfig.SUPABASE_URL}/storage/v1/object/public/$USERS_BUCKET/$imageFileName"

    /**
     * Handles exceptions and emits an appropriate [ApiResource.Error].
     */
    private fun handleThrowable(throwable: Throwable): ApiResource<Unit> {
        val isNetworkError = throwable is HttpException
        return ApiResource.Error(
            isNetworkError = isNetworkError,
            errorCode = (throwable as? HttpException)?.response?.code, // null if not network error
            errorBody = throwable.message
        )
    }
}