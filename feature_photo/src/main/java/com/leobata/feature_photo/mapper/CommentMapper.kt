package com.leobata.feature_photo.mapper

import javax.inject.Inject
import com.leobata.domain.model.Comment as DomainComment
import com.leobata.feature_photo.model.Comment as ViewComment

/**
 * Maps comments between View and Domain
 */
internal class CommentMapper @Inject constructor() {
    /**
     * Maps Comment from Domain to View.
     *
     * @param domainComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toView(domainComment: DomainComment): ViewComment =
        ViewComment(
            id = domainComment.id,
            name = domainComment.name,
            email = domainComment.email,
            body = domainComment.body
        )

    /**
     * Maps Comment from Domain to View.
     *
     * @param domainCommentList the Comment list to be converted.
     *
     * @return the converted Comment list
     */
    fun toView(domainCommentList: List<DomainComment>): List<ViewComment> =
        domainCommentList.take(20).map {
            ViewComment(
                id = it.id,
                name = it.name,
                email = it.email,
                body = it.body
            )
        }

    /**
     * Maps Comment from View to Domain.
     *
     * @param viewComment the Comment to be converted.
     *
     * @return the converted Comment
     */
    fun toDomain(viewComment: ViewComment): DomainComment =
        DomainComment(
            id = viewComment.id,
            name = viewComment.name,
            email = viewComment.email,
            body = viewComment.body
        )
}