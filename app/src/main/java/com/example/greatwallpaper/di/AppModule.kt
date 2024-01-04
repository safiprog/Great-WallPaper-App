package com.example.greatwallpaper.di

import com.example.greatwallpaper.Utils.Constants.Base_url
import com.example.greatwallpaper.data.WallpaperRepostiryImpl
import com.example.greatwallpaper.data.api.PicsumApi
import com.example.greatwallpaper.domain.repository.WallpaperRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {


    companion object {
        @Provides
        @Singleton
        fun provideRetrofitApi(): PicsumApi {
            return Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PicsumApi::class.java)
        }
    }

        @Binds
        @Singleton
        fun provideWallPaperRepo(repository: WallpaperRepostiryImpl): WallpaperRepository



}