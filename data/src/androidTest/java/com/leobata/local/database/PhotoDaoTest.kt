package com.leobata.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.leobata.data.local.dao.PhotoDao
import com.leobata.data.local.database.GalleryDatabase
import com.leobata.data.local.model.Photo
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
class PhotoDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: GalleryDatabase

    private lateinit var dao: PhotoDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.photoDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertPhotoTesting() = runBlockingTest {
        val photo = Photo(
            id = 1,
            albumId = 2,
            title = "Title 1",
            thumbnailUrl = "http://thumb.com",
            url = "http://pic.com"
        )
        dao.insertPhoto(photo)

        val resultPhoto = dao.getPhotoById(photo.id)
        assertThat(resultPhoto).isEqualTo(photo)
    }

    @Test
    fun insertPhotoListTesting() = runBlockingTest {
        val photoList = listOf(
            Photo(
                id = 1,
                albumId = 2,
                title = "Title 1",
                thumbnailUrl = "http://thumb.com",
                url = "http://pic.com"
            ),
            Photo(
                id = 2,
                albumId = 2,
                title = "Title 2",
                thumbnailUrl = "http://thumb.com",
                url = "http://pic.com"
            )
        )
        dao.insertAllPhotos(photoList)

        val list = dao.getPhotos().first()
        assertThat(list).containsExactlyElementsIn(photoList)
    }

    @Test
    fun deletePhotoTesting() = runBlockingTest {
        val photo = Photo(
            id = 1,
            albumId = 2,
            title = "Title 1",
            thumbnailUrl = "http://thumb.com",
            url = "http://pic.com"
        )
        dao.insertPhoto(photo)
        dao.deletePhotoById(photo.id)

        val list = dao.getPhotos().first()
        assertThat(list).doesNotContain(photo)
    }

    @Test
    fun updatePhotoTesting() = runBlockingTest {
        val photo = Photo(
            id = 1,
            albumId = 2,
            title = "Title 1",
            thumbnailUrl = "http://thumb.com",
            url = "http://pic.com"
        )
        dao.insertPhoto(photo)
        var resultPhoto = dao.getPhotoById(photo.id)
        assertThat(resultPhoto).isEqualTo(photo)

        val updatedPhoto = Photo(
            id = 1,
            albumId = 56,
            title = "New Title 1",
            thumbnailUrl = "http://thumb.com",
            url = "http://pic.com"
        )
        dao.updatePhoto(updatedPhoto)
        resultPhoto = dao.getPhotoById(photo.id)
        assertThat(resultPhoto).isEqualTo(updatedPhoto)
    }

    @Test
    fun deleteAllPhotoTesting() = runBlockingTest {
        val photoList = listOf(
            Photo(
                id = 1,
                albumId = 2,
                title = "Title 1",
                thumbnailUrl = "http://thumb.com",
                url = "http://pic.com"
            ),
            Photo(
                id = 2,
                albumId = 2,
                title = "Title 2",
                thumbnailUrl = "http://thumb.com",
                url = "http://pic.com"
            )
        )
        dao.insertAllPhotos(photoList)
        var list = dao.getPhotos().first()
        assertThat(list).hasSize(2)
        dao.deleteAllPhotos()
        list = dao.getPhotos().first()
        assertThat(list).hasSize(0)
    }
}