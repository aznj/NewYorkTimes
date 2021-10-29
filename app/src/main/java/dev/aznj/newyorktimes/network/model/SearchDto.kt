package dev.aznj.newyorktimes.network.model

import com.google.gson.annotations.SerializedName

data class SearchDto(
    @SerializedName("_id")
    var id: String,

    @SerializedName("abstract")
    var abstract: String,

    @SerializedName("pub_date")
    var publishedDate: String,
)