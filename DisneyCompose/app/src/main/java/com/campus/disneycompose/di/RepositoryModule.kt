package com.campus.disneycompose.di

import com.campus.disneycompose.network.DisneyService
import com.campus.disneycompose.persistence.PosterDao
import com.campus.disneycompose.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        disneyService: DisneyService,
        posterDao: PosterDao
    ): MainRepository {
        return MainRepository(disneyService, posterDao)
    }
}
