package dev.sobhy.jameya.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.sobhy.jameya.data.repository.AuthRepositoryImpl
import dev.sobhy.jameya.domain.repository.AuthRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAuthRepo(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}