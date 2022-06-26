package pgm.poolp.leboncoin.data

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TitleTest {

    private lateinit var title: Title

    @Before
    fun setUp() {
        title = Title(
            id = 1,
            albumId = 1,
            title = "accusamus beatae ad facilis cum similique qui sunt",
            url = "https://via.placeholder.com/600/92c952",
            thumbnailUrl = "https://via.placeholder.com/150/92c952"
        )
    }

    @Test
    fun test_toString() {
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", title.toString())
    }
}