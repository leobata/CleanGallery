package com.leobata.local.datasource

import com.google.common.truth.Truth.assertThat
import com.leobata.data.local.dao.CommentDao
import com.leobata.data.local.datasource.CommentLocalDataSource
import com.leobata.data.local.mapper.CommentMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import com.leobata.data.local.model.Comment as LocalComment
import com.leobata.data.repository.model.Comment as RepoComment

@ExperimentalCoroutinesApi
class CommentLocalDataSourceTest {
    private val mockDao = mockk<CommentDao>(relaxed = true)
    private val mockMapper = mockk<CommentMapper>(relaxed = true)
    private val commentLocalDataSource = CommentLocalDataSource(mockDao, mockMapper)

    @Before
    fun setup() {

        coEvery { mockDao.getCommentByPhotoId(any()) } returns flow {
            emit(
                listOf(
                    LocalComment(
                        id = 1,
                        postId = 2,
                        photoId = 11,
                        name = "John Doe",
                        email = "test@test.com",
                        body = "Test comment"
                    ),
                    LocalComment(
                        id = 2,
                        postId = 2,
                        photoId = 11,
                        name = "Random User",
                        email = "test@test.com",
                        body = "Test comment"
                    )
                )
            )
        }

        every { mockMapper.toRepo(LocalComment()) } returns RepoComment(
            id = 1,
            photoId = 11,
            name = "John Doe",
            email = "test@test.com",
            body = "Test comment"
        )
    }

    @Test
    fun `check if all comments are properly retrieved by photo ID`() = runBlockingTest {
        val requestedComments = commentLocalDataSource.loadCommentsByPhotoId(11).first()
        assertThat(requestedComments).hasSize(2)
    }
}