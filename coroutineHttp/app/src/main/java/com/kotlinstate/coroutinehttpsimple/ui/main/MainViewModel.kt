package com.kotlinstate.coroutinehttpsimple.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinstate.coroutinehttpsimple.ui.http.apiService
import com.medi.comm.network.result.awaitOrError
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {




    public fun requestHomeData() {

        // 作用局局部变量
        viewModelScope.launch {

            // 请求1
            val (result, message) = async {
                apiService.getAritrilList(0)
            }.awaitOrError()

            message?.composeException { throwable, code, message ->
                // 网络异常
            } ?: run {
                //请求成功  result?.data
            }

        }

    }
}
