package ai.naver.carelink.data.datasources.remote

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage

object SupabaseConfig {
    private const val SUPABASE_URL = "https://zgfscrmvrhbbmhyqdcgb.supabase.co"
    private const val SUPABASE_KEY = "sb_publishable_XKDYhLTBRB8AnnS5_FiUVw_ZwzeFiD8"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Auth)      // Plugin đăng nhập
        install(Postgrest) // Plugin gọi Database
        install(Realtime)  // Plugin lắng nghe dữ liệu thời gian thực
        install(Storage)   // Plugin upload ảnh
    }
}