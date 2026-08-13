package com.echcoding.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="wish-title")
    val title: String = "",
    @ColumnInfo(name="wish-desc")
    val description: String = ""
)

object DummyWish {
    val wishlist = listOf(
        Wish(title="Google Watch 2", description = "An Android Watch"),
        Wish(title="Playstation 5", description = "Sony's latest video game console"),
        Wish(title="Automatic Pet Feeder", description = "An Android Pet Feeder for two pets"),
        Wish(title="Security Outdoor Camera", description = "A wireless camera for outdoor use")
    )
}