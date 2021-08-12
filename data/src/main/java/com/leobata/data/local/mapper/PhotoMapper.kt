package com.leobata.data.local.mapper

import javax.inject.Inject
import com.leobata.data.local.model.Photo as LocalPhoto
import com.leobata.data.repository.model.Photo as RepoPhoto

/**
 * Maps photos between Local Data Source and Repository
 */
class PhotoMapper @Inject constructor() {
    /**
     * Maps Photo from Local to Repo.
     *
     * @param localPhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toRepo(localPhoto: LocalPhoto): RepoPhoto =
        RepoPhoto(
            id = localPhoto.id,
            title = localPhoto.title,
            url = localPhoto.url,
            thumbnailUrl = localPhoto.thumbnailUrl
        )

    /**
     * Maps Photo from Repo to Local.
     *
     * @param repoPhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toLocal(repoPhoto: RepoPhoto): LocalPhoto =
        LocalPhoto(
            id = repoPhoto.id,
            title = repoPhoto.title,
            url = repoPhoto.url,
            thumbnailUrl = repoPhoto.thumbnailUrl
        )

    /**
     * Maps Photos from Repo to Local.
     *
     * @param repoPhotoList the Photo list to be converted.
     *
     * @return the converted Photo list
     */
    fun toLocal(repoPhotoList: List<RepoPhoto>): List<LocalPhoto> =
        repoPhotoList.map {
            LocalPhoto(
                id = it.id,
                title = it.title,
                url = it.url,
                thumbnailUrl = it.thumbnailUrl
            )
        }
}