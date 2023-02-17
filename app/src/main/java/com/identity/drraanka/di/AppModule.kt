package com.identity.drraanka.di

import android.content.Context
import com.identity.drraanka.DrRaankaApplication
import com.identity.drraanka.data.remote.api.RaankaApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(): Context {
        return DrRaankaApplication.instance!!.applicationContext
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://164.52.205.22/ranka_api_dev/")
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RaankaApiService {
        return retrofit.create(RaankaApiService::class.java)
    }
}