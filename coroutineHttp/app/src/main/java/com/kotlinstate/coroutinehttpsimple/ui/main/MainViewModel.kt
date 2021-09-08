package com.kotlinstate.coroutinehttpsimple.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinstate.coroutinehttpsimple.ui.http.apiService
import com.medi.comm.network.result.awaitOrError
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    public fun requestGlobal() {
        // 全局写法
        GlobalScope.launch {
            val (result3, message3) = async {
                apiService.getBanner()
            }.awaitOrError()
        }
    }

    public fun requestHomeData() {

        // 作用局局部变量
        viewModelScope.launch {

            // 请求1
            val (result1, message1) = async {
                apiService.getAritrilList(0)
            }.awaitOrError()

            message1?.composeException { throwable, code, message ->
                // 网络异常
            } ?: run {
                //请求成功  result?.data
            }

            //请求2
            val (result2, message2) = async {
                apiService.getBanner()
            }.awaitOrError()

            message2?.composeException { throwable, code, message ->

            } ?: run {
                //请求成功  result2?.data
            }
        }


    }
}
