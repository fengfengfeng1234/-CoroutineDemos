package com.kotlinstate.coroutinehttpsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.kotlinstate.coroutinehttpsimple.ui.http.apiService
import com.kotlinstate.coroutinehttpsimple.ui.http.request.ApiResponse
import com.kotlinstate.coroutinehttpsimple.ui.http.request.ClassifyResponse
import com.kotlinstate.coroutinehttpsimple.ui.http.scope
import com.kotlinstate.coroutinehttpsimple.ui.main.MainFragment
import com.medi.comm.network.result.Result
import com.medi.comm.network.result.awaitOrError
import com.medi.comm.network.result.getErrorMessage
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.SelectClause1
import me.hgj.jetpackmvvm.demo.data.repository.request.BusinessException
import java.lang.Exception
import java.lang.Runnable
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 官网参考
 *  https://developer.android.com/topic/libraries/architecture/coroutines?hl=zh-cn#lifecyclescope
 *
 *  lifecycleScope 在 DESTROYED 会取消  lifecycleScope 内容
          lifecycle.currentState < Lifecycle.State.INITIALIZED
 *   具体参考 https://blog.csdn.net/yechaoa/article/details/118077968
 */
class HttpCanCel2Activity : AppCompatActivity() {
    var tv: Button? = null
    lateinit var content: TextView;
    var httpJob:Job?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.http_cancel)

        content = findViewById<TextView>(R.id.content)
        findViewById<TextView>(R.id.synchronizeBt).setOnClickListener {
            httpJob=  lifecycleScope.launch {

                val  http =async {
                    apiService.getBanner()
                }


                http.await()


            }
        }
    }

}









