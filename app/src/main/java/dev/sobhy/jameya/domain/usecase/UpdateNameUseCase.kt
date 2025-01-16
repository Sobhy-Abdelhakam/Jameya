package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.repository.ProfileRepository

class UpdateNameUseCase(private val repository: ProfileRepository) {
    suspend fun execute(name: String) = repository.updateName(name)
}