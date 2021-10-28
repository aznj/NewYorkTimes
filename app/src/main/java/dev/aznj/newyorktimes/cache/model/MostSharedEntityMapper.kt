package dev.aznj.newyorktimes.cache.model

import dev.aznj.newyorktimes.domain.DomainMapper
import dev.aznj.newyorktimes.domain.model.MostPopular

class MostSharedEntityMapper : DomainMapper<MostSharedEntity, MostPopular> {
    override fun mapToDomainModel(model: MostSharedEntity): MostPopular {
        return MostPopular(
            id = model.id,
            title = model.title,
            abstract = model.abstract,
            publishedDate = model.publishedDate
        )
    }

    override fun mapFromDomainModel(domainModel: MostPopular): MostSharedEntity {
        return MostSharedEntity(
            id = domainModel.id,
            title = domainModel.title,
            abstract = domainModel.abstract,
            publishedDate = domainModel.publishedDate
        )
    }

    fun fromEntityList(initial: List<MostSharedEntity>): List<MostPopular>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<MostPopular>): List<MostSharedEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}