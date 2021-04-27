package com.campus.disneycompose.repository

import androidx.annotation.WorkerThread
import com.campus.disneycompose.model.Poster
import com.campus.disneycompose.network.DisneyService
import com.campus.disneycompose.persistence.PosterDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val disneyService: DisneyService,
    private val posterDao: PosterDao
) : Repository {

    @WorkerThread
    fun loadDisneyPosters(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val posters: List<Poster> = posterDao.getPosterList()
        if (posters.isEmpty()) {
            disneyService.fetchDisneyPosterList()
                .suspendOnSuccess {
                    data.whatIfNotNull {
                        posterDao.insertPosterList(it)
                        emit(it)
                        onSuccess()
                    }
                }
                .onError {
                    onError(message())
                }
                .onException {
                    onError(message())
                }
        } else {
            emit(posters)
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)
}
