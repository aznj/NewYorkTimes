package dev.aznj.newyorktimes.cache.model

import dev.aznj.newyorktimes.domain.DomainMapper
import dev.aznj.newyorktimes.domain.model.MostPopular

class MostEmailedEntityMapper : DomainMapper<MostEmailedEntity, MostPopular> {
    override fun mapToDomainModel(model: MostEmailedEntity): MostPopular {
        return MostPopular(
            id = model.id,
            title = model.title,
            abstract = model.abstract,
            publishedDate = model.publishedDate
        )
    }

    override fun mapFromDomainModel(domainModel: MostPopular): MostEmailedEntity {
        return MostEmailedEntity(
            id = domainModel.id,
            title = domainModel.title,
            abstract = domainModel.abstract,
            publishedDate = domainModel.publishedDate
        )
    }

    fun fromEntityList(initial: List<MostEmailedEntity>): List<MostPopular>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<MostPopular>): List<MostEmailedEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}