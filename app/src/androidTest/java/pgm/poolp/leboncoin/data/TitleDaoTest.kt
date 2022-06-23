package pgm.poolp.leboncoin.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TitleDaoTest {
    private lateinit var database: LeboncoinRoomDatabase
    private lateinit var titleDao: TitleDao

    private val titleA = Title(
        id = 1,
        albumId = 1,
        title = "accusamus beatae ad facilis cum similique qui sunt",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
    )

    private val titleB = Title(
        id = 2,
        albumId = 1,
        title = "reprehenderit est deserunt velit ipsam",
        url = "https://via.placeholder.com/600/771796",
        thumbnailUrl = "https://via.placeholder.com/150/771796"
    )

    private val titleC = Title(
        id = 3,
        albumId = 1,
        title = "officia porro iure quia iusto qui ipsa ut modi",
        url = "https://via.placeholder.com/600/24f355",
        thumbnailUrl = "https://via.placeholder.com/150/24f355"
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, LeboncoinRoomDatabase::class.java).build()
        titleDao = database.titleDao()

        // Insert titles in non-alphabetical order to test that results are sorted by id
        titleDao.insertAll(listOf(titleB, titleC, titleA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetTitles() = runBlocking {
        val titleList = titleDao.getTitles().first()
        assertThat(titleList.size, Matchers.equalTo(3))

        // Ensure title list is sorted by id
        assertThat(titleList[0], Matchers.equalTo(titleA))
        assertThat(titleList[1], Matchers.equalTo(titleB))
        assertThat(titleList[2], Matchers.equalTo(titleC))
    }
}
