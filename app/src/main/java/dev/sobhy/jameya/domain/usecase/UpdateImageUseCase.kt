package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.repository.ProfileRepository

class UpdateImageUseCase(private val repository: ProfileRepository) {
    suspend fun execute(image: ByteArray) = repository.updateImage(image)
}