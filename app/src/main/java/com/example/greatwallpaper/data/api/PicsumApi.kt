package com.example.greatwallpaper.data.api

import com.example.greatwallpaper.Utils.Constants.End_point
import com.example.greatwallpaper.data.Model.PicsumItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsumApi {


//    url=https://picsum.photos/v2/list?page=1&limit=100

    @GET(End_point)
    suspend fun getWallPaperImg(@Query("page")page:Int=1,@Query("limit")limit:Int=100):List<PicsumItem>
}