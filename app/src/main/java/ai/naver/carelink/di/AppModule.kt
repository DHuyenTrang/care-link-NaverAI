package ai.naver.carelink.di

import ai.naver.carelink.data.datasources.local.AppStore
import ai.naver.carelink.di.network_module.networkModule
import org.koin.dsl.module

val appModule = module {
    single { AppStore(get()) }
    includes(networkModule)
    includes(repositoryModule)
    includes(viewModelModule)
    includes(useCaseModule)
}