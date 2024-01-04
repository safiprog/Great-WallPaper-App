package com.example.greatwallpaper.presentation.ViewModel

import com.example.greatwallpaper.domain.entity.WallpaperLink

open class WallpaperUiState {
    object Loading:WallpaperUiState()

    object EmptyList:WallpaperUiState()

    data class Success(val data: List<WallpaperLink>?):WallpaperUiState()

    data class Error(val message:String):WallpaperUiState()
}