package com.example.greatwallpaper.data

import android.util.Log
import com.example.greatwallpaper.Utils.Resource
import com.example.greatwallpaper.data.Model.PicsumItem
import com.example.greatwallpaper.data.api.PicsumApi
import com.example.greatwallpaper.domain.entity.WallpaperLink
import com.example.greatwallpaper.domain.repository.WallpaperRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WallpaperRepostiryImpl @Inject constructor(val picsumApi: PicsumApi) : WallpaperRepository {
    override fun getPicsumjImages(): Flow<Resource<List<WallpaperLink>>> = flow {


        try {
            val response = picsumApi.getWallPaperImg()
            Log.d("checkar", response.toString())

            response?.let {
                val wallpaperLink = response.map {
                    WallpaperLink(it.download_url)
                }
                emit(Resource.Success(wallpaperLink))
            }
        } catch (e: Exception) {
            Log.d("checkar", e.message.toString())
            emit(Resource.Error(null, e.message ?: "Network connection Error"))
        }

    }
}