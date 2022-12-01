package com.example.testtecnicojairo.dagger

import com.example.testtecnicojairo.BuildConfig
import com.example.testtecnicojairo.dashboard.constants.DashboardConstants
import com.example.testtecnicojairo.dashboard.data.dataSource.TopRatedMoviesDataSource
import com.example.testtecnicojairo.dashboard.framework.data.config.retrofit.DashboardApiService
import com.example.testtecnicojairo.dashboard.framework.data.implementation.TopRatedMoviesDataSourceImpl
import com.example.testtecnicojairo.splash.data.dataSource.SessionDataSource
import com.example.testtecnicojairo.splash.framework.data.config.retrofit.SessionApiService
import com.example.testtecnicojairo.splash.framework.data.implementation.SessionDataSourceImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {
    @Provides
    fun providesBaseUrl() = DashboardConstants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { _chain ->
                val url = _chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", DashboardConstants.API_KEY)
                    .build()
                _chain.proceed(_chain.request().newBuilder().url(url).build())
            }
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(DashboardApiService::class.java)

    @Provides
    @Singleton
    fun provideSessionApiService(retrofit: Retrofit) = retrofit.create(SessionApiService::class.java)

    @Provides
    @Singleton
    fun provideTopRatedMovies(topRatedMoviesDataSourceImpl: TopRatedMoviesDataSourceImpl):
            TopRatedMoviesDataSource = topRatedMoviesDataSourceImpl

    @Provides
    @Singleton
    fun provideSessionId(sessionDataSourceImpl: SessionDataSourceImpl):
            SessionDataSource = sessionDataSourceImpl

}