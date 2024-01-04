package com.example.greatwallpaper.Utils

sealed class Resource<T>(val data:T?=null,val message:String?=null) {
    class Success<T>(data: T):Resource<T>(data)

    class Error<T>(data: T?=null,message: String):Resource<T>(data,message)
}


//object test{
//    @JvmStatic
//    fun main(args: Array<String>) {
//
//        val test1=Resource.Success(43)
//        val test2=Resource.Error(4,"safi")
//    }
//}