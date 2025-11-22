package ai.naver.carelink.di

import ai.naver.carelink.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get()) }
}