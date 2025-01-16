package dev.sobhy.jameya.domain.usecase

import dev.sobhy.jameya.domain.repository.ProfileRepository

class GetUserUseCase(private val profileRepository: ProfileRepository) {
    fun execute() = profileRepository.getUser()
}