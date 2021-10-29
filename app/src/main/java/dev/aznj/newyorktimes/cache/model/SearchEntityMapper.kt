package dev.aznj.newyorktimes.cache.model

import dev.aznj.newyorktimes.domain.DomainMapper
import dev.aznj.newyorktimes.domain.model.Search

class SearchEntityMapper : DomainMapper<SearchEntity, Search> {
    override fun mapToDomainModel(model: SearchEntity): Search {
        return Search(
            id = model.id,
            abstract = model.title,
            publishedDate = model.publishedDate
        )
    }

    override fun mapFromDomainModel(domainModel: Search): SearchEntity {
        return SearchEntity(
            id = domainModel.id,
            title = domainModel.abstract,
            publishedDate = domainModel.publishedDate,
        )
    }

    fun fromEntityList(initial: List<SearchEntity>): List<Search>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Search>): List<SearchEntity>{
        return initial.map { mapFromDomainModel(it) }
    }
}