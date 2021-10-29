package dev.aznj.newyorktimes.cache

import dev.aznj.newyorktimes.cache.model.MostEmailedEntity

class MostEmailedDaoFake(
    private val appDatabaseFake: AppDatabaseFake
) : MostEmailedDao {
    override suspend fun insertMostEmailed(mostEmailed: List<MostEmailedEntity>): LongArray {
        appDatabaseFake.mostEmailed.addAll(mostEmailed)
        return longArrayOf(1)
    }

    override suspend fun getAllMostEmailed(): List<MostEmailedEntity> {
        return appDatabaseFake.mostEmailed
    }
}