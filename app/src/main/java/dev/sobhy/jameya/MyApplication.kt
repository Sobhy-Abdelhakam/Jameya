package dev.sobhy.jameya

import android.app.Application
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY,
        ){
            install(Postgrest)
        }
    }
}