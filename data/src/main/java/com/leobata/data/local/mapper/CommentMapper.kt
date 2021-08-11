package com.leobata.data.local.mapper

import javax.inject.Inject
import com.leobata.data.local.model.Comment as LocalComment
import com.leobata.data.repository.model.Comment as RepoComment

/**
 * Maps comments between Local Data Source and Repository
 */
class CommentMapper @Inject constructor() {
    /**
     * Maps Comment from Local to Repo.
     *
     * @param localComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toRepo(localComment: LocalComment): RepoComment =
        RepoComment(
            id = localComment.id,
            photoId = localComment.photoId,
            name = localComment.name,
            email = localComment.email,
            body = localComment.body
        )

    /**
     * Maps Comment from Repo to Local.
     *
     * @param repoComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toLocal(repoComment: RepoComment): LocalComment =
        LocalComment(
            id = repoComment.id,
            photoId = repoComment.photoId,
            name = repoComment.name,
            email = repoComment.email,
            body = repoComment.body
        )
}