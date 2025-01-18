package dev.sobhy.jameya.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.sobhy.jameya.BuildConfig
import dev.sobhy.jameya.data.datastore.DataStoreManager
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
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
            install(Storage)
        }
    }
    @Provides
    @Singleton
    fun providePostgrest(supabaseClient: SupabaseClient): Postgrest {
        return supabaseClient.postgrest
    }
    @Provides
    @Singleton
    fun provideStorage(supabaseClient: SupabaseClient): Storage {
        return supabaseClient.storage
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}