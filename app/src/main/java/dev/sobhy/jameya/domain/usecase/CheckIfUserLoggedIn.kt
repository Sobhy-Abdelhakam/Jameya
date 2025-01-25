package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.repository.AuthRepository

class CheckIfUserLoggedInUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn()
    }
}