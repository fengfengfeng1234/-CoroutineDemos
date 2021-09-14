package com.kotlinstate.coroutinehttpsimple

import android.util.Log
import com.kotlinstate.coroutinehttpsimple.ui.http.request.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import com.medi.comm.network.result.Result
import me.hgj.jetpackmvvm.demo.data.repository.request.BusinessException
import me.hgj.jetpackmvvm.demo.data.repository.request.NetException
import me.hgj.jetpackmvvm.demo.data.repository.request.ResultCode
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * Created by luyao
 * on 2019/4/10 9:41
 */
open class BaseRepository {


    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            Result.of(getErrorMessage(e))
        }
    }

    suspend fun <T : Any> executeResponse(
        response: ApiResponse<T>
    ): Result<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                var exception = BusinessException(response.errorCode, response.errorMsg)
                Result.of(getErrorMessage(exception))
            } else {
                Result.of(response.data)
            }
        }
    }

    private fun getErrorMessage(error: Throwable): NetException {
        val msg: String
        val code: Int
        if (error is UnknownHostException
            || error is ProtocolException
            || error is ConnectException
        ) {
            msg = "网络异常，请检查网络环境！"
            code = ResultCode.NET_WORK_ERROR
        } else if (error is JSONException) {
            msg = "数据格式异常，请稍后再试！"
            code = ResultCode.JSON_PARSE_ERROR
        } else if (error is SSLException) {
            msg = "连接异常或者服务器异常，请稍后再试！"
            code = ResultCode.NET_WORK_ERROR
        } else if (error is SocketTimeoutException || error is ConnectTimeoutException) {
            msg = "连接服务器超时，请检查网络环境"
            code = ResultCode.NET_WORK_ERROR
        } else if (error is HttpException) { //http异常
            code = error.code()
            msg = "请求失败，请稍后再试 $code"
        } else if (error is BusinessException) { // 业务异常
            code = error.code
            msg = if (error.message == null) {
                ""
            } else {
                error.message!!;
            }

        } else {
            msg = "请求失败，请稍后再试"
            code = ResultCode.UNKNOWN_ERROR
        }
        return NetException(error, code, msg)
    }


}