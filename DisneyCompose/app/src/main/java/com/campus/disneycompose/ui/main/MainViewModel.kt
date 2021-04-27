package com.campus.disneycompose.ui.main

import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.annotation.WorkerThread
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import coil.ImageLoader
import com.campus.disneycompose.base.LiveCoroutinesViewModel
import com.campus.disneycompose.model.Poster
import com.campus.disneycompose.repository.DetailRepository
import com.campus.disneycompose.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val detailRepository: DetailRepository,
    val imageLoader: ImageLoader
) : LiveCoroutinesViewModel() {

    private var _posterList: MutableLiveData<Boolean> = MutableLiveData(true)
    val posterList: LiveData<List<Poster>>

    private var _posterDetails: LiveData<Poster> = MutableLiveData()
    val posterDetails: LiveData<Poster> get() = _posterDetails

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast

    init {
        posterList = _posterList.switchMap {
            _isLoading.postValue(true)
            launchOnViewModelScope {
                this.mainRepository.loadDisneyPosters(
                    onSuccess = { _isLoading.postValue(false) },
                    onError = { _toast.postValue(it) }
                ).asLiveData()
            }
        }
    }

    @WorkerThread
    fun getPoster(id: Long) {
        _posterDetails = detailRepository.getPosterById(id)
    }

    @MainThread
    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }
}
