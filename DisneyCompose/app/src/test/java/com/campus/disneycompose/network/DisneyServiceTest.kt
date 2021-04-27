package com.campus.disneycompose.network

import com.campus.disneycompose.MainCoroutinesRule
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class DisneyServiceTest : com.campus.disneycompose.network.ApiAbstract<DisneyService>() {

    private lateinit var service: DisneyService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(DisneyService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchDisneyPosterListTest() = runBlocking {
        enqueueResponse("/DisneyPosters.json")
        val response = service.fetchDisneyPosterList()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)
        mockWebServer.takeRequest()

        assertThat(responseBody[0].id, `is`(0L))
        assertThat(responseBody[0].name, `is`("Frozen II"))
        assertThat(responseBody[0].release, `is`("2019"))
    }
}
