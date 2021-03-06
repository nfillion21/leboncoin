package pgm.poolp.leboncoin.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TitleRepository @Inject constructor(titleDao: TitleDao) {

    val allTitles: Flow<List<Title>> = titleDao.getTitles()
}
