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
class HttpCanCelActivity : AppCompatActivity() {
    var tv: Button? = null
    lateinit var content: TextView;
    var httpJob:Job?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.http_cancel)

        content = findViewById<TextView>(R.id.content)
        findViewById<TextView>(R.id.synchronizeBt).setOnClickListener {
            httpJob=  lifecycleScope.launch {

                val (result, message) = HttpX.getBanner()
                delay(3000)
                val (result2, message2) = HttpX.getProjecTitle()


//                message?.composeException { _, _, message ->
//                    content.text = "异常 $message "
//                } ?: kotlin.run {
//                    content.text = "正常返回 = ${result.toString()}"
//                }
//
//
//                message2?.composeException { _, _, message ->
//                    content.text = "异常 $message "
//                } ?: kotlin.run {
//                    content.text = "正常返回 = ${result.toString()}"
//                }


            }
        }
        findViewById<TextView>(R.id.parallelBt).setOnClickListener {
            httpJob = lifecycleScope.launch {
                delay(3000)
                var result1 = async {
                    HttpX.getBanner()
                }
                var result2 = async {
                    HttpX.getProjecTitle()
                }


                /**
                 * 并发优势所在
                 */
                var (rsp1, msg) = result1.await()
                var (rsp2, msg2) = result2.await()

                msg?.composeException { _, _, message ->
                    content.text = "异常 $message "
                } ?: kotlin.run {
                    content.text = "正常返回 = ${rsp1.toString()}"
                }

                msg2?.composeException { _, _, message ->
                    content.text = "异常 $message "
                } ?: kotlin.run {
                    content.text = "正常返回 = ${rsp2.toString()}"
                }


            }
        }

        findViewById<Button>(R.id.cancelBt).setOnClickListener {
            httpJob?.cancel()
        }


    }

    /**
     *  绑定生命周期 请求
     */
    private fun requestBindLife() {
        lifecycleScope.launch {
            Log.e("HttpCanCel", "延迟 网络-------requestBindLife 1")
            delay(3000)
            Log.e("HttpCanCel", "延迟 网络-------requestBindLife 2")
            val (result3, message3) = async {
                apiService.getProjecTitle()
            }.awaitOrError()
        }
    }

    /**
     *  全局网络请求
     */
    private fun requestGlobal() {
        // 全局写法
        GlobalScope.launch {
            Log.e("HttpCanCel", "延迟 网络-------requestGlobal 1")
            delay(3000)
            Log.e("HttpCanCel", "延迟 网络-------requestGlobal 2")
            val (result3, message3) = async {
                apiService.getBanner()
            }.awaitOrError()
        }
    }


}

private suspend fun <T> Deferred<T>.awaitOrError(): Any {

    val result = await()

    return Result.of(result)

}


fun <T> CoroutineScope.asyncWithCatch1(block: suspend CoroutineScope.() -> T): Deferred<T> {
    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("MainFragment", "CoroutineExceptionHandler got $exception")
    }
    return async(handler) {
        block()
    }
}

fun <T> CoroutineScope.asyncWithCatch(block: suspend CoroutineScope.() -> T): CatchableDeferred<T> {
    var errHandler = ErrorHandlerWrapper()
    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("MainFragment", "CoroutineExceptionHandler got $exception")
    }
    return CatchableDeferred(
        async(handler) { block() },
        errHandler
    )
}

class ErrorHandlerWrapper {
    var handler: ((Throwable) -> Unit)? = null;
    operator fun invoke(throwable: Throwable) {
        handler?.invoke(throwable) ?: throw throwable
    }
}

class CatchableDeferred<T>(
    private val target: Deferred<T>,
    private val errorCatcher: ErrorHandlerWrapper
) {

    val onAwait: SelectClause1<T> = target.onAwait

    @ExperimentalCoroutinesApi
    fun getCompleted(): T = target.getCompleted();

    @ExperimentalCoroutinesApi
    fun getCompletionExceptionOrNull(): Throwable? = target.getCompletionExceptionOrNull()

    fun onError(handler: (Throwable) -> Unit) = apply {
        errorCatcher.handler = handler
    }

    suspend fun await(): T = target.await();


}
