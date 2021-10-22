package dev.aznj.newyorktimes.cache.model

import dev.aznj.newyorktimes.domain.DomainMapper
import dev.aznj.newyorktimes.domain.model.MostPopular

class MostViewedEntityMapper : DomainMapper<MostViewedEntity, MostPopular> {
    override fun mapToDomainModel(model: MostViewedEntity): MostPopular {
        return MostPopular(
            id = model.id,
            title = model.title,
            abstract = model.abstract,
            publishedDate = model.publishedDate
        )
    }

    override fun mapFromDomainModel(domainModel: MostPopular): MostViewedEntity {
        return MostViewedEntity(
            id = domainModel.id,
            title = domainModel.title,
            abstract = domainModel.abstract,
            publishedDate = domainModel.publishedDate
        )
    }

    fun fromEntityList(initial: List<MostViewedEntity>): List<MostPopular>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<MostPopular>): List<MostViewedEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}