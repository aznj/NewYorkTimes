package dev.aznj.newyorktimes.domain.model

data class MostPopular(
    val id: Long,
    val title: String,
    val abstract: String,
    val publishedDate: String,
)