package dev.aznj.newyorktimes.cache

import dev.aznj.newyorktimes.cache.model.MostViewedEntity

class MostViewedDaoFake(
    private val appDatabaseFake: AppDatabaseFake
) : MostViewedDao {
    override suspend fun insertMostVieweds(mostVieweds: List<MostViewedEntity>): LongArray {
        appDatabaseFake.mostViewed.addAll(mostVieweds)
        return longArrayOf(1)
    }

    override suspend fun getAllMostVieweds(): List<MostViewedEntity> {
        return appDatabaseFake.mostViewed
    }

}