package com.example.greatwallpaper.presentation.View


import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.greatwallpaper.databinding.ActivityMainBinding
import com.example.greatwallpaper.domain.entity.WallpaperLink
import com.example.greatwallpaper.presentation.Adaptor.ClickLisner
import com.example.greatwallpaper.presentation.Adaptor.myAdaptor
import com.example.greatwallpaper.presentation.ViewModel.MainViewModel
import com.example.greatwallpaper.presentation.ViewModel.WallpaperUiState

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1, setup View
        // 2 Collect state ->no wallpaer in beginning
        // 3. update our wallpaer from reset api

        setupViews()
        collectUiState()
        mainViewModel.fetchWallpapers()
//        setWallPaperToScreen(url)

    }


    private fun setupViews() {
        binding.recycView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun collectUiState() {
        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.wallPaperList.collect() {
                when (it) {
                    is WallpaperUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is WallpaperUiState.EmptyList -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(
                            this@MainActivity,
                            "Images is Loding Please waitn",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is WallpaperUiState.Success -> {

                        binding.progressBar.visibility = View.GONE
                        populateDataInRecyclerView(it.data!!)
                    }

                    is WallpaperUiState.Error -> {
                        Toast.makeText(this@MainActivity, "Sorry Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun populateDataInRecyclerView(data: List<WallpaperLink>) {


        val myAdaptor = myAdaptor(data,object:ClickLisner{
            override fun clickListnerAt(url: Drawable) {

                try {
                    setWallPaperToScreen(url)
                    Toast.makeText(applicationContext, "Wallpaper set Successfuly ", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "error is $e", Toast.LENGTH_SHORT).show()
                }
            }

        })
        binding.recycView.adapter = myAdaptor
    }

    private fun setWallPaperToScreen(url: Drawable) {
        val wallpaperManager = WallpaperManager.getInstance(this)
        val bitmap = (url as BitmapDrawable).bitmap
        lifecycleScope.launch(Dispatchers.IO) {
            wallpaperManager.setBitmap(bitmap)


        }


    }

//    override fun clickListnerAt(position: Drawable) {
//        Toast.makeText(this, "safi hero hai $position", Toast.LENGTH_SHORT).show()
//        Log.d("clickCheck", "count $position")
//
//        try {
//            setWallPaperToScreen(position)
//        } catch (e: Exception) {
//            Toast.makeText(this, "error is $e", Toast.LENGTH_SHORT).show()
//        }
//
//    }


}