package pgm.poolp.leboncoin.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("select * from title order by id")
    fun getTitles(): Flow<List<Title>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(titles: List<Title>)
}
