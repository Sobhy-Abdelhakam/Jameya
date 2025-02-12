package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.model.User
import dev.sobhy.jameya.domain.repository.AuthRepository

class RetrieveUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): User? {
        return authRepository.retrieveUser()
    }
}