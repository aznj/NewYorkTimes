package dev.aznj.newyorktimes.cache

import dev.aznj.newyorktimes.cache.model.MostEmailedEntity
import dev.aznj.newyorktimes.cache.model.MostSharedEntity
import dev.aznj.newyorktimes.cache.model.MostViewedEntity

class AppDatabaseFake {

    val mostViewed = mutableListOf<MostViewedEntity>()
    val mostEmailed = mutableListOf<MostEmailedEntity>()
    val mostShared = mutableListOf<MostSharedEntity>()
}