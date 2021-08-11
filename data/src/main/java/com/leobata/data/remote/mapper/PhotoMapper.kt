package com.leobata.data.remote.mapper

import javax.inject.Inject
import com.leobata.data.repository.model.Photo as RepoPhoto
import com.leobata.data.remote.model.Photo as RemotePhoto

/**
 * Maps photos between Remote Data Source and Repository
 */
class PhotoMapper @Inject constructor() {
    /**
     * Maps Photo from Remote to Repo.
     *
     * @param remotePhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toRepo(remotePhoto: RemotePhoto): RepoPhoto =
        RepoPhoto(
            id = remotePhoto.id,
            title = remotePhoto.title,
            url = remotePhoto.url,
            thumbnailUrl = remotePhoto.thumbnailUrl
        )

    /**
     * Maps Photo from Repo to Remote.
     *
     * @param repoPhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toRemote(repoPhoto: RepoPhoto): RemotePhoto =
        RemotePhoto(
            id = repoPhoto.id,
            title = repoPhoto.title,
            url = repoPhoto.url,
            thumbnailUrl = repoPhoto.thumbnailUrl
        )
}