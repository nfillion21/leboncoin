package pgm.poolp.leboncoin.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pgm.poolp.leboncoin.data.LeboncoinRoomDatabase
import pgm.poolp.leboncoin.data.Title

class TitleDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {

            val client = HttpClient(Android)
            val url = inputData.getString(TITLES_KEY_URL)
            val result: HttpResponse = client.get(url!!)

            if (result.status == HttpStatusCode.OK)
            {
                val titleType = object : TypeToken<List<Title>>() {}.type
                val titleList: List<Title> = Gson().fromJson(result.readText(), titleType)
                val database = LeboncoinRoomDatabase.getInstance(applicationContext)
                database.titleDao().insertAll(titleList)

                Result.success()
            } else {
                Log.e(TAG, "Error seeding database - no valid url")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "TitleDatabaseWorker"
        const val TITLES_KEY_URL = "TITLES_KEY_URL"
    }
}
