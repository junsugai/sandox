package com.campus.disneycompose.repository

import com.campus.disneycompose.persistence.PosterDao
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val posterDao: PosterDao
) : Repository {

    fun getPosterById(id: Long) = posterDao.getPoster(id)
}
