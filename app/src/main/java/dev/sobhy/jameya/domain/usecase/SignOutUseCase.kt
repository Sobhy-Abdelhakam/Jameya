package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.repository.AuthRepository

class SignOutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        authRepository.signOut()
    }
}