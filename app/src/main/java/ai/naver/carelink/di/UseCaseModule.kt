package ai.naver.carelink.di

import ai.naver.carelink.domain.usecase.CheckUserSessionUseCase
import ai.naver.carelink.domain.usecase.LoginUseCase
import ai.naver.carelink.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { CheckUserSessionUseCase(get()) }
}