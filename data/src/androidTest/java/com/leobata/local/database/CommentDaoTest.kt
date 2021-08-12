package com.leobata.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.leobata.data.local.dao.CommentDao
import com.leobata.data.local.database.GalleryDatabase
import com.leobata.data.local.model.Comment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class CommentDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: GalleryDatabase

    private lateinit var dao: CommentDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.commentDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertCommentTesting() = runBlockingTest {
        val comment = Comment(
            id = 1,
            postId = 2,
            photoId = 11,
            name = "John Doe",
            email = "test@test.com",
            body = "Test comment"
        )
        dao.insertComment(comment)

        val list = dao.getComments().first()
        assertThat(list).contains(comment)
    }

    @Test
    fun insertCommentListTesting() = runBlockingTest {
        val commentList = listOf(
            Comment(
                id = 1,
                postId = 2,
                photoId = 11,
                name = "John Doe",
                email = "test@test.com",
                body = "Test comment"
            ),
            Comment(
                id = 2,
                postId = 2,
                photoId = 11,
                name = "Random Guy",
                email = "test@test.com",
                body = "Test comment"
            )
        )
        dao.insertAllComments(commentList)

        val list = dao.getComments().first()
        assertThat(list).containsExactlyElementsIn(commentList)
    }

    @Test
    fun deleteCommentTesting() = runBlockingTest {
        val comment = Comment(
            id = 1,
            postId = 2,
            photoId = 11,
            name = "John Doe",
            email = "test@test.com",
            body = "Test comment"
        )
        dao.insertComment(comment)
        dao.deleteCommentById(comment.id)

        val list = dao.getComments().first()
        assertThat(list).doesNotContain(comment)

    }

    @Test
    fun updateCommentTesting() = runBlockingTest {
        val comment = Comment(
            id = 1,
            postId = 2,
            photoId = 11,
            name = "John Doe",
            email = "test@test.com",
            body = "Test comment"
        )
        dao.insertComment(comment)
        var resultComment = dao.getCommentById(comment.id)
        assertThat(resultComment).isEqualTo(comment)

        val updatedComment = Comment(
            id = 1,
            postId = 56,
            photoId = 11,
            name = "John Doe",
            email = "test@test.com",
            body = "New test comment"
        )
        dao.updateComment(updatedComment)
        resultComment = dao.getCommentById(comment.id)
        assertThat(resultComment).isEqualTo(updatedComment)
    }

    @Test
    fun deleteAllCommentsTesting() = runBlockingTest {
        val commentList = listOf(
            Comment(
                id = 1,
                postId = 2,
                photoId = 11,
                name = "John Doe",
                email = "test@test.com",
                body = "Test comment"
            ),
            Comment(
                id = 2,
                postId = 2,
                photoId = 11,
                name = "Random Guy",
                email = "test@test.com",
                body = "Test comment"
            )
        )
        dao.insertAllComments(commentList)
        var list = dao.getComments().first()
        assertThat(list).hasSize(2)
        dao.deleteAllComments()
        list = dao.getComments().first()
        assertThat(list).hasSize(0)
    }
}