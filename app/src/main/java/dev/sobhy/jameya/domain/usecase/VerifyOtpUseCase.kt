package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor (private val repository: AuthRepository) {
    suspend operator fun invoke(phoneNumber: String, otp: String) = repository.verifyOtp(phoneNumber, otp)

}