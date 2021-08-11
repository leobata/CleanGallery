package com.leobata.data.remote.mapper

import javax.inject.Inject
import com.leobata.data.remote.model.Comment as RemoteComment
import com.leobata.data.repository.model.Comment as RepoComment

/**
 * Maps comments between Repository and Domain
 */
class CommentMapper @Inject constructor() {
    /**
     * Maps Comment from Domain to Repo.
     *
     * @param remoteComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toRepo(remoteComment: RemoteComment): RepoComment =
        RepoComment(
            id = remoteComment.id,
            name = remoteComment.name,
            email = remoteComment.email,
            body = remoteComment.body
        )

    /**
     * Maps Comment from Repo to Domain.
     *
     * @param repoComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toRemote(repoComment: RepoComment): RemoteComment =
        RemoteComment(
            id = repoComment.id,
            name = repoComment.name,
            email = repoComment.email,
            body = repoComment.body
        )
}