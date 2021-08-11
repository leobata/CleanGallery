package com.leobata.data.repository.mapper

import javax.inject.Inject
import com.leobata.data.repository.model.Photo as RepoPhoto
import com.leobata.domain.model.Photo as DomainPhoto

/**
 * Maps photos between Repository and Domain
 */
internal class PhotoMapper @Inject constructor() {
    /**
     * Maps Photo from Domain to Repo.
     *
     * @param domainPhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toRepo(domainPhoto: DomainPhoto): RepoPhoto =
        RepoPhoto(
            id = domainPhoto.id,
            title = domainPhoto.title,
            url = domainPhoto.url,
            thumbnailUrl = domainPhoto.thumbnailUrl
        )

    /**
     * Maps Photo from Repo to Domain.
     *
     * @param repoPhotoList the Photo list to be converted.
     *
     * @return the converted Photo list
     */
    fun toDomain(repoPhotoList: List<RepoPhoto>): List<DomainPhoto> =
        repoPhotoList.map {
            DomainPhoto(
                id = it.id,
                title = it.title,
                url = it.url,
                thumbnailUrl = it.thumbnailUrl
            )
        }

    /**
     * Maps Photo from Repo to Domain.
     *
     * @param repoPhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toDomain(repoPhoto: RepoPhoto): DomainPhoto =
        DomainPhoto(
            id = repoPhoto.id,
            title = repoPhoto.title,
            url = repoPhoto.url,
            thumbnailUrl = repoPhoto.thumbnailUrl
        )
}