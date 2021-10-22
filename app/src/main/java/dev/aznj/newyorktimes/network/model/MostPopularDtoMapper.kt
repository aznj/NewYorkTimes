package dev.aznj.newyorktimes.network.model

import dev.aznj.newyorktimes.domain.DomainMapper
import dev.aznj.newyorktimes.domain.model.MostPopular

class MostPopularDtoMapper : DomainMapper<MostPopularDto, MostPopular> {
    override fun mapToDomainModel(model: MostPopularDto): MostPopular {
        return MostPopular(
            id = model.id,
            title = model.title,
            abstract = model.abstract,
            publishedDate = model.publishedDate,
        )
    }

    override fun mapFromDomainModel(domainModel: MostPopular): MostPopularDto {
        return MostPopularDto(
            id = domainModel.id,
            title = domainModel.title,
            abstract = domainModel.abstract,
            publishedDate = domainModel.publishedDate,
        )
    }

    fun toDomainList(initial: List<MostPopularDto>): List<MostPopular>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<MostPopular>): List<MostPopularDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}