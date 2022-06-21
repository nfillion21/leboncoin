package pgm.poolp.leboncoin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import pgm.poolp.leboncoin.utilities.DATABASE_NAME
import pgm.poolp.leboncoin.workers.TitleDatabaseWorker
import pgm.poolp.leboncoin.workers.TitleDatabaseWorker.Companion.TITLE_LIST_URL

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Title::class], version = 1, exportSchema = false)
abstract class LeboncoinRoomDatabase : RoomDatabase() {

    abstract fun titleDao(): TitleDao

    companion object {
        @Volatile
        private var instance: LeboncoinRoomDatabase? = null

        fun getInstance(context: Context): LeboncoinRoomDatabase {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LeboncoinRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                LeboncoinRoomDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(ChampionDatabaseCallback(context))
                .build()
        }

        private class ChampionDatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val workManager = WorkManager.getInstance(context)

                val requestChampions = OneTimeWorkRequestBuilder<TitleDatabaseWorker>()
                    .setInputData(workDataOf(TITLE_LIST_URL to TITLE_LIST_URL))
                    .build()
                workManager.enqueue(requestChampions)
            }
        }
    }
}