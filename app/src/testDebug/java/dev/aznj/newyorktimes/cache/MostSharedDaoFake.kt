package dev.aznj.newyorktimes.cache

import dev.aznj.newyorktimes.cache.model.MostSharedEntity

class MostSharedDaoFake(
    private val appDatabaseFake: AppDatabaseFake
) : MostSharedDao {
    override suspend fun insertMostShared(mostShared: List<MostSharedEntity>): LongArray {
        appDatabaseFake.mostShared.addAll(mostShared)
        return longArrayOf(1)
    }

    override suspend fun getAllMostShared(): List<MostSharedEntity> {
        return appDatabaseFake.mostShared
    }

}