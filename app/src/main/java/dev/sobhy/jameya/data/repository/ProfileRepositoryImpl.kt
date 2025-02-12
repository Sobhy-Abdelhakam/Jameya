package dev.sobhy.jameya.data.repository

import android.util.Log
import coil3.network.HttpException
import dev.sobhy.jameya.BuildConfig
import dev.sobhy.jameya.core.response.ApiResource
import dev.sobhy.jameya.data.dto.UserDto
import dev.sobhy.jameya.data.mappers.toDomain
import dev.sobhy.jameya.domain.model.User
import dev.sobhy.jameya.domain.repository.ProfileRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: Auth,
    private val postgrest: Postgrest,
    private val storage: Storage,
) : ProfileRepository {
    companion object {
        private const val USERS_TABLE = "profiles"
        private const val USERS_BUCKET = "users"
        private const val IMAGE_COLUMN = "image_url"
        private const val NAME_COLUMN = "full_name"
    }

    override fun getUser(): Flow<ApiResource<User>> = flow {
        emit(ApiResource.Loading)
        val user = auth.currentUserOrNull()?.id
        Log.d("userId", user.toString())
        val response = runCatching {
            postgrest.from(USERS_TABLE)
                .select(){
                    filter { {
                        eq("id", user!!)
                    } }
                }
                .decodeSingleOrNull<UserDto>()
        }.getOrNull()
        if (response == null) {
            emit(ApiResource.Error(false, null, "Failed to fetch user data"))
            return@flow
        }
        emit(ApiResource.Success(response.toDomain()))
    }.catch { handleThrowable(it) }

    override suspend fun updateImage(imageName: String, image: ByteArray?): ApiResource<Unit> {
        return runCatching {
            if (image == null){
                updateUserData(IMAGE_COLUMN, null)
                ApiResource.Success(Unit)
            }
            val imageFileName = "profile_$imageName.png"
            val imageUrl = uploadImage(imageFileName, image!!)
            Log.d("repo", "Image upload successfully")
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

    private suspend fun updateUserData(column: String, value: String?) {
        try {
            val response = auth.updateUser {
                data(mapOf(column to value)) // Correct way to update metadata
            }
            Log.d("name", response.userMetadata?.get("full_name").toString())
        } catch (e: Exception) {
            Log.e("updateUserData", "Error updating user data ${e.message}")
        }

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
        return when (throwable) {
            is HttpException -> ApiResource.Error(
                isNetworkError = true,
                errorCode = throwable.response.code,
                errorBody = throwable.message
            )
            is IOException -> ApiResource.Error(
                isNetworkError = true,
                errorCode = null,
                errorBody = "Network connection error"
            )
            else -> ApiResource.Error(
                isNetworkError = false,
                errorCode = null,
                errorBody = throwable.localizedMessage ?: "Unexpected error"
            )
        }
    }
}