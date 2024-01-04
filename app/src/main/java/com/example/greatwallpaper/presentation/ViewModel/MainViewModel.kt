package com.example.greatwallpaper.presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greatwallpaper.Utils.Resource
import com.example.greatwallpaper.domain.entity.WallpaperLink
import com.example.greatwallpaper.domain.repository.WallpaperRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WallpaperRepository) : ViewModel() {

    private val _wallpaperList: MutableStateFlow<WallpaperUiState> =
        MutableStateFlow(WallpaperUiState.Loading)

    val wallPaperList get() = _wallpaperList

    fun fetchWallpapers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPicsumjImages().collect() {

                when (it) {

                    is Resource.Success -> {

                        if (it.data.isNullOrEmpty()) {
                            _wallpaperList.update { WallpaperUiState.Loading }
                        } else {
                            val temp=it.data
                            _wallpaperList.update { WallpaperUiState.Success(temp) }
                        }
                    }

                    is Resource.Error -> {
                        _wallpaperList.update { WallpaperUiState.EmptyList }
                    }
                }
            }
        }
    }
}