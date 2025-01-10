package dev.sobhy.jameya.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dev.sobhy.jameya.BuildConfig
import dev.sobhy.jameya.data.login.AuthRepositoryImpl
import dev.sobhy.jameya.domain.repository.AuthRepository
import dev.sobhy.jameya.domain.usecase.SendOtpUseCase
import dev.sobhy.jameya.domain.usecase.VerifyOtpUseCase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient{
        return createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY,
        ){
            install(Auth)
            install(Postgrest)
        }
    }

    @Provides
    @Singleton
    fun provideAuthRepo(supabaseClient: SupabaseClient): AuthRepository{
        return AuthRepositoryImpl(supabaseClient)
    }
    @Provides
    @Singleton
    fun provideSendOtpUseCase(authRepository: AuthRepository): SendOtpUseCase {
        return SendOtpUseCase(authRepository)
    }
    @Provides
    @Singleton
    fun provideVerifyOtpUseCase(authRepository: AuthRepository): VerifyOtpUseCase {
        return VerifyOtpUseCase(authRepository)
    }
}