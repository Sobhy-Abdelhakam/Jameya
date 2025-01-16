package dev.sobhy.jameya.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.sobhy.jameya.domain.repository.AuthRepository
import dev.sobhy.jameya.domain.repository.ProfileRepository
import dev.sobhy.jameya.domain.usecase.GetUserUseCase
import dev.sobhy.jameya.domain.usecase.SendOtpUseCase
import dev.sobhy.jameya.domain.usecase.UpdateImageUseCase
import dev.sobhy.jameya.domain.usecase.UpdateNameUseCase
import dev.sobhy.jameya.domain.usecase.VerifyOtpUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideSendOtpUseCase(authRepository: AuthRepository): SendOtpUseCase {
        return SendOtpUseCase(authRepository)
    }
    @Provides
    fun provideVerifyOtpUseCase(authRepository: AuthRepository): VerifyOtpUseCase {
        return VerifyOtpUseCase(authRepository)
    }
    @Provides
    fun provideGetUser(profileRepository: ProfileRepository): GetUserUseCase {
        return GetUserUseCase(profileRepository)
    }
    @Provides
    fun provideUpdateName(profileRepository: ProfileRepository): UpdateNameUseCase {
        return UpdateNameUseCase(profileRepository)
    }
    @Provides
    fun provideUpdateImage(profileRepository: ProfileRepository): UpdateImageUseCase {
        return UpdateImageUseCase(profileRepository)
    }
}