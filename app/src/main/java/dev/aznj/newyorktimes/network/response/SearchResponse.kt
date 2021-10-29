package dev.aznj.newyorktimes.network.response

import com.google.gson.annotations.SerializedName
import dev.aznj.newyorktimes.network.model.SearchDto

data class SearchResponse(
    @SerializedName("response")
    val response: Docs
)

data class Docs(
    @SerializedName("docs")
    val searchResults: List<SearchDto>
)
