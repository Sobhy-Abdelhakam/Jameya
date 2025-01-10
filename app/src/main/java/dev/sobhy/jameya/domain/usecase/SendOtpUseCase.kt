package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.repository.AuthRepository
import javax.inject.Inject

class SendOtpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phoneNumber: String) = authRepository.sendOtp(phoneNumber)
}