package dev.aznj.newyorktimes.network.response

import com.google.gson.annotations.SerializedName
import dev.aznj.newyorktimes.network.model.MostPopularDto

data class MostPopularResponse(

    @SerializedName("results")
    val mostPopulars: List<MostPopularDto>
)