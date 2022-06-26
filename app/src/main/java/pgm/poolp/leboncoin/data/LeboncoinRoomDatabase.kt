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
import pgm.poolp.leboncoin.utilities.TITLE_LIST_URL
import pgm.poolp.leboncoin.workers.TitleDatabaseWorker
import pgm.poolp.leboncoin.workers.TitleDatabaseWorker.Companion.TITLES_KEY_URL

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
                .addCallback(TitleDatabaseCallback(context))
                .build()
        }

        private class TitleDatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val workManager = WorkManager.getInstance(context)

                val requestTitles = OneTimeWorkRequestBuilder<TitleDatabaseWorker>()
                    .setInputData(workDataOf(TITLES_KEY_URL to TITLE_LIST_URL))
                    .build()
                workManager.enqueue(requestTitles)
            }
        }
    }
}