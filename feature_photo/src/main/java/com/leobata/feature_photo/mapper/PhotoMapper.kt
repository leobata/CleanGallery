package com.leobata.feature_photo.mapper

import javax.inject.Inject
import com.leobata.domain.model.Photo as DomainPhoto
import com.leobata.domain.model.PhotoWithComments as DomainPhotoWithComments
import com.leobata.feature_photo.model.Photo as ViewPhoto
import com.leobata.feature_photo.model.PhotoWithComments as ViewPhotoWithComments

/**
 * Maps photos between View and Domain
 */
internal class PhotoMapper @Inject constructor(private val commentMapper: CommentMapper) {
    /**
     * Maps Photo from Domain to View.
     *
     * @param domainPhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toView(domainPhoto: DomainPhoto): ViewPhoto =
        ViewPhoto(
            id = domainPhoto.id,
            title = domainPhoto.title,
            url = domainPhoto.url,
            thumbnailUrl = domainPhoto.thumbnailUrl
        )

    /**
     * Maps Photo with comments from Domain to View.
     *
     * @param domainPhotoWithComments the Photo with comments to be converted.
     *
     * @return the converted Photo with comments
     */
    fun toView(domainPhotoWithComments: DomainPhotoWithComments): ViewPhotoWithComments =
        ViewPhotoWithComments(
            photo = toView(domainPhotoWithComments.photo),
            comments = commentMapper.toView(domainPhotoWithComments.comments.take(20))
        )

    /**
     * Maps Photo from Domain to View.
     *
     * @param domainPhotoList the Photo list to be converted.
     *
     * @return the converted Photo list
     */
    fun toView(domainPhotoList: List<DomainPhoto>): List<ViewPhoto> =
        domainPhotoList.map {
            ViewPhoto(
                id = it.id,
                title = it.title,
                url = it.url,
                thumbnailUrl = it.thumbnailUrl
            )
        }.sortedBy { it.title }

    /**
     * Maps Photo with comments from Domain to View.
     *
     * @param domainPhotoWithCommentsList the Photo with comments list to be converted.
     *
     * @return the converted Photo with comments list
     */
    @JvmName("toView1")
    fun toView(domainPhotoWithCommentsList: List<DomainPhotoWithComments>): List<ViewPhotoWithComments> =
        domainPhotoWithCommentsList.map {
            ViewPhotoWithComments(
                photo = toView(it.photo),
                comments = commentMapper.toView(it.comments.take(20))
            )
        }

    /**
     * Maps Photo from View to Domain.
     *
     * @param viewPhoto the Photo to be converted.
     *
     * @return the converted Photo
     */
    fun toDomain(viewPhoto: ViewPhoto): DomainPhoto =
        DomainPhoto(
            id = viewPhoto.id,
            title = viewPhoto.title,
            url = viewPhoto.url,
            thumbnailUrl = viewPhoto.thumbnailUrl
        )
}