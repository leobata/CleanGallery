package com.leobata.local.datasource

import com.google.common.truth.Truth.assertThat
import com.leobata.data.local.dao.PhotoDao
import com.leobata.data.local.datasource.PhotoLocalDataSource
import com.leobata.data.local.mapper.PhotoMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import com.leobata.data.local.model.Photo as LocalPhoto
import com.leobata.data.repository.model.Photo as RepoPhoto

@ExperimentalCoroutinesApi
class PhotoLocalDataSourceTest {
    private val mockDao = mockk<PhotoDao>(relaxed = true)
    private val mockMapper = mockk<PhotoMapper>(relaxed = true)
    private val photoLocalDataSource = PhotoLocalDataSource(mockDao, mockMapper)

    @Before
    fun setup() {
        coEvery { mockDao.getPhotoById(any()) } returns LocalPhoto(
            id = 1,
            albumId = 2,
            title = "Title 1",
            thumbnailUrl = "http://thumb.com",
            url = "http://pic.com"
        )

        coEvery { mockDao.getPhotos() } returns flow {
            emit(
                listOf(
                    LocalPhoto(
                        id = 1,
                        albumId = 2,
                        title = "Title 1",
                        thumbnailUrl = "http://thumb.com",
                        url = "http://pic.com"
                    ),
                    LocalPhoto(
                        id = 2,
                        albumId = 2,
                        title = "Title 1",
                        thumbnailUrl = "http://thumb.com",
                        url = "http://pic.com"
                    )
                )
            )
        }

        every { mockMapper.toRepo(any()) } returns RepoPhoto(
            id = 1,
            title = "Title 1",
            thumbnailUrl = "http://thumb.com",
            url = "http://pic.com"
        )
    }

    @Test
    fun `check if photo is properly retrieved by ID`() = runBlockingTest {
        val requestedPhoto = photoLocalDataSource.loadPhotoById(1)
        assertThat(requestedPhoto?.id).isEqualTo(1)
    }

    @Test
    fun `check if all photos are properly retrieved`() = runBlockingTest {
        val requestedPhotos = photoLocalDataSource.loadAllPhotos().first()
        assertThat(requestedPhotos).hasSize(2)
    }
}