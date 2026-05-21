package com.ez.weather4you.data.local.forecast.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context= context,
        klass = W4YRoomDatabase::class.java,
        name = "W4Y_DATABASE"
    ).build()

    @Singleton
    @Provides
    fun providesW4YDao(database: W4YRoomDatabase) = database.getW4YDao()
}