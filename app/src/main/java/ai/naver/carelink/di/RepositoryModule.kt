package ai.naver.carelink.di

import ai.naver.carelink.data.repositories.AuthRepositoryImpl
import ai.naver.carelink.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}