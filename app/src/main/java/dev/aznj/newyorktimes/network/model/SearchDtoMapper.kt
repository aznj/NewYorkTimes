package dev.aznj.newyorktimes.network.model

import dev.aznj.newyorktimes.domain.DomainMapper
import dev.aznj.newyorktimes.domain.model.Search

class SearchDtoMapper : DomainMapper<SearchDto, Search> {
    override fun mapToDomainModel(model: SearchDto): Search {
        return Search(
            id = model.id,
            abstract = model.abstract,
            publishedDate = model.publishedDate
        )
    }

    override fun mapFromDomainModel(domainModel: Search): SearchDto {
        return SearchDto(
            id = domainModel.id,
            abstract = domainModel.abstract,
            publishedDate = domainModel.publishedDate
        )
    }

    fun toDomainList(initial: List<SearchDto>): List<Search>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Search>): List<SearchDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}