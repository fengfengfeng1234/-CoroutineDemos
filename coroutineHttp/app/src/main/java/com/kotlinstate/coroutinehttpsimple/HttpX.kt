package com.kotlinstate.coroutinehttpsimple

import com.kotlinstate.coroutinehttpsimple.ui.http.apiService
import com.kotlinstate.coroutinehttpsimple.ui.http.request.ApiResponse
import com.kotlinstate.coroutinehttpsimple.ui.http.request.BannerResponse
import com.kotlinstate.coroutinehttpsimple.ui.http.request.ClassifyResponse
import com.medi.comm.network.result.Result
import retrofit2.http.GET

object HttpX : BaseRepository() {

    suspend fun getBanner(): Result<ArrayList<BannerResponse>> {
        return safeApiCall(call = { executeResponse(apiService.getBanner()) },)
    }


    suspend fun getProjecTitle(): Result<ArrayList<ClassifyResponse>> {
        return safeApiCall(call = { executeResponse(apiService.getProjecTitle()) },)
    }


}