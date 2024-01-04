package com.example.greatwallpaper.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.example.greatwallpaper.data.Model.PicsumItem
import com.example.greatwallpaper.domain.entity.WallpaperLink
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {


    fun getPicsumjImages(): Flow<com.example.greatwallpaper.Utils.Resource<List<WallpaperLink>>>

}