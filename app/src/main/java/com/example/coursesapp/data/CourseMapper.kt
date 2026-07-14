package com.example.coursesapp.data

import com.example.coursesapp.data.bd.CourseEntity
import com.example.coursesapp.data.network.CourseDTO
import com.example.coursesapp.domain.CourseDomain
import java.time.LocalDate

object CourseMapper{

    fun toDomainFromDto(courseDTO: CourseDTO): CourseDomain {
        return CourseDomain(
            id = courseDTO.id,
            title = courseDTO.title,
            text = courseDTO.text,
            price = courseDTO.price,
            rate = courseDTO.rate,
            startDate = LocalDate.parse(courseDTO.startDate),
            hasLike = courseDTO.hasLike,
            publishDate = LocalDate.parse(courseDTO.publishDate)
        )
    }

    fun toDomainListFromDTO(dtos: List<CourseDTO>): List<CourseDomain> {
        return dtos.map { toDomainFromDto(it) }
    }

    fun toDomainFromEntity(courseEntity: CourseEntity): CourseDomain {
        return CourseDomain(
            id = courseEntity.id,
            title = courseEntity.title,
            text = courseEntity.text,
            price = courseEntity.price,
            rate = courseEntity.rate,
            startDate = LocalDate.parse(courseEntity.startDate),
            hasLike = courseEntity.hasLike,
            publishDate = LocalDate.parse(courseEntity.publishDate)
        )
    }

    fun toDomainListFromEntities(entities: List<CourseEntity>): List<CourseDomain> {
        return entities.map { toDomainFromEntity(it) }
    }

    fun toEntity(courseDomain: CourseDomain): CourseEntity {
        return CourseEntity(
            id = courseDomain.id,
            title = courseDomain.title,
            text = courseDomain.text,
            price = courseDomain.price,
            rate = courseDomain.rate,
            startDate = courseDomain.startDate.toString(),
            hasLike = courseDomain.hasLike,
            publishDate = courseDomain.publishDate.toString()
        )
    }

    fun toEntityList(domains: List<CourseDomain>): List<CourseEntity> {
        return domains.map { toEntity(it) }
    }


}