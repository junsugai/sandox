package com.campus.disneycompose.persistence

import com.campus.disneycompose.model.Poster
import com.campus.disneycompose.utils.MockTestUtil.mockPosterList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PosterDaoTest : LocalDatabase() {

    private lateinit var posterDao: PosterDao

    @Before
    fun init() {
        posterDao = db.posterDao()
    }

    @Test
    fun insertAndLoadPosterListTest() = runBlocking {
        val mockDataList = mockPosterList()
        posterDao.insertPosterList(mockDataList)

        val loadFromDB = posterDao.getPosterList()
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = Poster.mock()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }
}
