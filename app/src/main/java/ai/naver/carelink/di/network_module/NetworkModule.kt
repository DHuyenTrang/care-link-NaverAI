package ai.naver.carelink.di.network_module

import ai.naver.carelink.data.datasources.remote.SupabaseConfig
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
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import io.ktor.websocket.WebSocketDeflateExtension.Companion.install
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    single { provideGson() }
    singleOf(::NetworkServiceImpl) { bind<NetworkService>() }

    single<SupabaseClient> { SupabaseConfig.client }

    single<Auth> { get<SupabaseClient>().auth }
    single<Postgrest> { get<SupabaseClient>().postgrest }
    single<Realtime> { get<SupabaseClient>().realtime }
    single<Storage> { get<SupabaseClient>().storage }
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}