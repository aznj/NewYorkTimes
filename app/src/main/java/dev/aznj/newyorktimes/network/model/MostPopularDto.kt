package dev.aznj.newyorktimes.network.model

import com.google.gson.annotations.SerializedName

data class MostPopularDto(

    @SerializedName("id")
    var id: Long,

    @SerializedName("title")
    var title: String,

    @SerializedName("abstract")
    var abstract: String,

    @SerializedName("published_date")
    var publishedDate: String,
)