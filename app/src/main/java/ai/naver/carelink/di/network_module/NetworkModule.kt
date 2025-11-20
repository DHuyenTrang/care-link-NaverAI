package ai.naver.carelink.di.network_module

import ai.naver.carelink.service.NetworkService
import ai.naver.carelink.service.NetworkServiceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module{
    single { provideGson() }
    singleOf(::NetworkServiceImpl) { bind<NetworkService>() }
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}