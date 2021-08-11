package com.leobata.data.repository.mapper

import javax.inject.Inject
import com.leobata.data.repository.model.Comment as RepoComment
import com.leobata.domain.model.Comment as DomainComment

/**
 * Maps comments between Repository and Domain
 */
internal class CommentMapper @Inject constructor() {
    /**
     * Maps Comment from Domain to Repo.
     *
     * @param domainComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toRepo(domainComment: DomainComment): RepoComment =
        RepoComment(
            id = domainComment.id,
            name = domainComment.name,
            email = domainComment.email,
            body = domainComment.body
        )

    /**
     * Maps Comment from Repo to Domain.
     *
     * @param repoCommentList the Comment list to be converted.
     *
     * @return the converted Comment list
     */
    fun toDomain(repoCommentList: List<RepoComment>): List<DomainComment> =
        repoCommentList.map {
            DomainComment(
                id = it.id,
                name = it.name,
                email = it.email,
                body = it.body
            )
        }

    /**
     * Maps Comment from Repo to Domain.
     *
     * @param repoComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toDomain(repoComment: RepoComment): DomainComment =
        DomainComment(
            id = repoComment.id,
            name = repoComment.name,
            email = repoComment.email,
            body = repoComment.body
        )
}