package pgm.poolp.leboncoin.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("select * from title order by titleId")
    fun getTitles(): Flow<List<Title>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(title: Title)

    @Query("delete from title")
    suspend fun deleteAll()

    @Query("select * from title where titleId = :titleId")
    fun getTitle(titleId: Int): Flow<Title>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(titles: List<Title>)
}
