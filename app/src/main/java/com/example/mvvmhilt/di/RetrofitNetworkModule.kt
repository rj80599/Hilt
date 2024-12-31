package com.example.mvvmhilt.di

import com.example.mvvmhilt.retrofit.ApiInterface
import com.example.mvvmhilt.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@InstallIn(ActivityComponent::class) // for the activity level object and scope will ActivityScope
@InstallIn(SingletonComponent::class)
@Module
class RetrofitNetworkModule {

//    @ActivityScoped
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun retroApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}