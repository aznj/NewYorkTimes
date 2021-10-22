package dev.aznj.newyorktimes.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mostViewed")
data class MostViewedEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "abstract")
    var abstract: String,

    @ColumnInfo(name = "published_date")
    var publishedDate: String,
) {
    constructor() : this(0, "", "", "")
}