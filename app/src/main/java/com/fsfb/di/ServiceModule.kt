package com.fsfb.di

import android.content.Context
import androidx.room.Room
import com.fsfb.database.AppDatabase
import com.fsfb.services.HomeScreenService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideHomeScreenService(retrofit: Retrofit) = retrofit.create<HomeScreenService>()

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "offline_db"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.roomDao()

}