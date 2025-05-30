package com.example.mlchallenge.di

import androidx.room.Room
import com.example.mlchallenge.data.local.AppDatabase
import com.example.mlchallenge.data.remote.ArticleApiService
import com.example.mlchallenge.data.repository.IArticleRepository
import com.example.mlchallenge.data.repository.IArticleRepositoryImpl
import com.example.mlchallenge.viewModel.ArticleListViewModel
import com.example.mlchallenge.viewModel.SearchViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // ViewModel
    viewModel { SearchViewModel(get()) }
    viewModel { ArticleListViewModel(get()) }

    // Repository
    single<IArticleRepository> { IArticleRepositoryImpl(get(), get()) }


    val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer APP_USR-6315200748403805-053017-21100b58734106372873be15d5c2a77b-181588843"
            )
            .build()
        chain.proceed(request)
    }

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logging)
        .build()

    single {
        Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API Service
    single<ArticleApiService> {
        get<Retrofit>().create(ArticleApiService::class.java)
    }

    // Room database
    single {
        Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                "articles_database"
            ).fallbackToDestructiveMigration(false).build()
    }

    // DAO
    single { get<AppDatabase>().searchHistoryDao() }
}


