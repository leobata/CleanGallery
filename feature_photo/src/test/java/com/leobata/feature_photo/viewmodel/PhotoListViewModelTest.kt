package com.leobata.feature_photo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.leobata.domain.usecase.photo.LoadAllPhotos
import com.leobata.feature_photo.mapper.PhotoMapper
import com.leobata.feature_photo.model.UIResponseState
import com.leobata.feature_photo.presentation.list.PhotoListViewModel
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
import com.leobata.domain.model.Photo as DomainPhoto

@ExperimentalCoroutinesApi
class PhotoListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val loadPhotosUseCase = mockk<LoadAllPhotos>(relaxed = true)
    private val photoMapper = mockk<PhotoMapper>(relaxed = true)
    private lateinit var viewModel: PhotoListViewModel

    @Before
    fun setup() {
        viewModel = PhotoListViewModel(loadPhotosUseCase, photoMapper)

        coEvery { loadPhotosUseCase() } returns flow {
            emit(listOf<DomainPhoto>())
        }

        every { photoMapper.toView(listOf<DomainPhoto>()) } returns listOf()
    }

    @Test
    fun `Photos are properly loaded on ViewModel`() = runBlockingTest {
        viewModel.fetchData()
        val result = viewModel.viewState.getOrAwaitValue() as UIResponseState.Success<*>
        assertThat(result.content).isNotNull()
    }
}