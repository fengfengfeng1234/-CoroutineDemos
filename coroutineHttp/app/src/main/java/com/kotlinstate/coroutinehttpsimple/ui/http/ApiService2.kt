package com.kotlinstate.coroutinehttpsimple.ui.http

import com.kotlinstate.coroutinehttpsimple.ui.http.request.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 网络API
 */
interface ApiService2 {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
        const val SERVER_URL1 = "https://wanandroid.com/"
    }




    /**
     * 获取banner数据
     */
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<ArrayList<BannerResponse>>



}