package ai.naver.carelink.di.network_module

import ai.naver.carelink.service.NetworkService
import ai.naver.carelink.service.NetworkServiceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module{
    single { provideGson() }
    singleOf(::NetworkServiceImpl) { bind<NetworkService>() }

    single {
        createSupabaseClient(
            supabaseUrl = "https://zgfscrmvrhbbmhyqdcgb.supabase.co",
            supabaseKey = "sb_publishable_XKDYhLTBRB8AnnS5_FiUVw_ZwzeFiD8"
        ) {
            install(Postgrest)
            install(Auth)
        }
    }

    single { get<SupabaseClient>().postgrest }
    single { get<SupabaseClient>().auth }
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}