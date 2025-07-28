package com.createfuture.takehome.di

import com.createfuture.takehome.data.remote.CFApiInterface
import com.createfuture.takehome.data.repository.CFRepositoryImpl
import com.createfuture.takehome.data.util.NetworkApiConstants
import com.createfuture.takehome.domain.repository.CFRepository
import com.createfuture.takehome.domain.usecase.GetCharactersUseCases
import com.createfuture.takehome.presentation.viewmodel.GetCharactersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Define all your class or interface when needs to injected in any other class.
 * I am using Koin here. It is purely kotlin. So it is not platform based.
 * We can re-use the below method in any KMP project.
 */
val createFutureModule = module {

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(NetworkApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(CFApiInterface::class.java)
    }

    single<CFRepository> {
        CFRepositoryImpl(get<CFApiInterface>())
    }

    single<GetCharactersUseCases> {
        GetCharactersUseCases(get<CFRepository>())
    }

    factory<GetCharactersViewModel> {
        GetCharactersViewModel(get<GetCharactersUseCases>())
    }

}