package dev.sobhy.jameya

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

@HiltAndroidApp
class MyApplication: Application()
