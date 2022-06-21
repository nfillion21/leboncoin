package pgm.poolp.leboncoin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Title(
    @PrimaryKey (autoGenerate = false) val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
