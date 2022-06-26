package pgm.poolp.leboncoin.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pgm.poolp.leboncoin.data.LeboncoinRoomDatabase
import pgm.poolp.leboncoin.data.TitleDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): LeboncoinRoomDatabase {
        return LeboncoinRoomDatabase.getInstance(context)
    }

    @Provides
    fun provideTitleDao(appDatabase: LeboncoinRoomDatabase): TitleDao {
        return appDatabase.titleDao()
    }
}
