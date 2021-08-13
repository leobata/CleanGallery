package com.leobata.feature_photo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.leobata.domain.model.Comment
import com.leobata.domain.usecase.comment.LoadComments
import com.leobata.feature_photo.mapper.CommentMapper
import com.leobata.feature_photo.model.Photo
import com.leobata.feature_photo.model.UIResponseState
import com.leobata.feature_photo.presentation.detail.PhotoDetailViewModel
import com.leobata.feature_photo.util.MainCoroutineRule
import com.leobata.feature_photo.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotoDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val loadCommentsUseCase = mockk<LoadComments>(relaxed = true)
    private val commentMapper = mockk<CommentMapper>(relaxed = true)
    private lateinit var viewModel: PhotoDetailViewModel

    @Before
    fun setup() {
        viewModel = PhotoDetailViewModel(loadCommentsUseCase, commentMapper)

        coEvery { loadCommentsUseCase(any()) } returns flow {
            emit(listOf<Comment>())
        }

        every { commentMapper.toView(listOf<Comment>()) } returns listOf()
    }

    @Test
    fun `Comments are properly loaded on ViewModel`() = runBlockingTest {
        viewModel.setArguments(Photo(11, "Title", "URL", "THUMB"))
        viewModel.fetchData()
        val result = viewModel.viewState.getOrAwaitValue() as UIResponseState.Success<*>
        assertThat(result.content).isNotNull()
    }
}