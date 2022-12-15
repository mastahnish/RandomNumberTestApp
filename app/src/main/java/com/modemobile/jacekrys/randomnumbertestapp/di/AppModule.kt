package com.modemobile.jacekrys.randomnumbertestapp.di

import android.content.Context
import android.content.SharedPreferences
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants
import com.modemobile.jacekrys.randomnumbertestapp.common.Constants.USER_PREFERENCES
import com.modemobile.jacekrys.randomnumbertestapp.data.remote.RandomNumberApi
import com.modemobile.jacekrys.randomnumbertestapp.data.repository.RandomNumberRepositoryImpl
import com.modemobile.jacekrys.randomnumbertestapp.domain.repository.RandomNumberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun createClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    @Provides
    @Singleton
    fun providesRandomNumberApi(): RandomNumberApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomNumberApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRandomNumberRepository(api: RandomNumberApi): RandomNumberRepository {
        return RandomNumberRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }

}
